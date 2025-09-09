package com.rige.strategy;

import com.rige.dto.request.StockRequest;
import com.rige.entities.InventoryMovementEntity;
import com.rige.entities.ProductEntity;
import com.rige.entities.StockEntity;
import com.rige.entities.WarehouseEntity;
import com.rige.enums.MovementType;
import com.rige.repositories.IInventoryMovementRepository;
import com.rige.repositories.IProductRepository;
import com.rige.repositories.IStockRepository;
import com.rige.repositories.IWarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("ADD")
@RequiredArgsConstructor
public class AddStockStrategy implements IStockStrategy {

    private final IStockRepository stockRepository;
    private final IProductRepository productRepository;
    private final IWarehouseRepository warehouseRepository;
    private final IInventoryMovementRepository movementRepository;

    @Override
    public StockEntity execute(StockRequest request) {
        ProductEntity product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        WarehouseEntity warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        StockEntity stock = stockRepository.findByProductAndWarehouse(product, warehouse)
                .orElse(StockEntity.builder()
                        .product(product)
                        .warehouse(warehouse)
                        .quantity(0)
                        .minQuantity(10)
                        .build());

        stock.setQuantity(stock.getQuantity() + request.getQuantity());
        stockRepository.save(stock);

        InventoryMovementEntity movement = InventoryMovementEntity.builder()
                .product(product)
                .toWarehouse(warehouse)
                .quantity(request.getQuantity())
                .type(MovementType.IN)
                .build();

        movementRepository.save(movement);

        return StockEntity.builder()
                .product(product)
                .warehouse(warehouse)
                .quantity(stock.getQuantity())
                .minQuantity(stock.getMinQuantity())
                .build();
    }
}
