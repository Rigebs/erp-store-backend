package com.rige.strategy;

import com.rige.dto.request.StockRequest;
import com.rige.entities.InventoryMovementEntity;
import com.rige.entities.ProductEntity;
import com.rige.entities.StockEntity;
import com.rige.entities.WarehouseEntity;
import com.rige.enums.MovementType;
import com.rige.enums.StockAction;
import com.rige.repositories.IInventoryMovementRepository;
import com.rige.repositories.IProductRepository;
import com.rige.repositories.IStockRepository;
import com.rige.repositories.IWarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("TRANSFER")
@RequiredArgsConstructor
public class TransferStockStrategy implements IStockStrategy {

    private final IStockRepository stockRepository;
    private final IProductRepository productRepository;
    private final IWarehouseRepository warehouseRepository;
    private final IInventoryMovementRepository movementRepository;

    @Override
    public StockEntity execute(StockRequest request) {
        RemoveStockStrategy removeStrategy = new RemoveStockStrategy(stockRepository,
                productRepository, warehouseRepository, movementRepository);
        removeStrategy.execute(StockRequest.builder()
                .productId(request.getProductId())
                .warehouseId(request.getWarehouseId())
                .quantity(request.getQuantity())
                .action(StockAction.REMOVE)
                .build());

        AddStockStrategy addStrategy = new AddStockStrategy(stockRepository,
                productRepository, warehouseRepository, movementRepository);
        StockEntity stock = addStrategy.execute(StockRequest.builder()
                .productId(request.getProductId())
                .warehouseId(request.getTargetWarehouseId())
                .quantity(request.getQuantity())
                .action(StockAction.ADD)
                .build());

        ProductEntity product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        WarehouseEntity fromWarehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Source warehouse not found"));

        WarehouseEntity toWarehouse = warehouseRepository.findById(request.getTargetWarehouseId())
                .orElseThrow(() -> new RuntimeException("Destination warehouse not found"));

        InventoryMovementEntity movement = InventoryMovementEntity.builder()
                .product(product)
                .fromWarehouse(fromWarehouse)
                .toWarehouse(toWarehouse)
                .quantity(request.getQuantity())
                .type(MovementType.TRANSFER)
                .build();

        movementRepository.save(movement);

        return stock;
    }
}
