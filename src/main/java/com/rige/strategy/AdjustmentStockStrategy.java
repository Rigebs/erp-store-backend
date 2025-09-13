package com.rige.strategy;

import com.rige.entities.InventoryMovementEntity;
import com.rige.entities.ProductEntity;
import com.rige.entities.StockEntity;
import com.rige.entities.WarehouseEntity;
import com.rige.enums.MovementType;
import com.rige.repositories.IInventoryMovementRepository;
import com.rige.repositories.IProductRepository;
import com.rige.repositories.IStockRepository;
import com.rige.repositories.IWarehouseRepository;
import com.rige.dto.request.StockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("ADJUSTMENT")
@RequiredArgsConstructor
public class AdjustmentStockStrategy implements IStockStrategy {

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
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        int oldQuantity = stock.getQuantity();

        if (request.getQuantity() >= 0) {
            stock.setQuantity(request.getQuantity());
        }

        if (request.getMinQuantity() > 0) {
            stock.setMinQuantity(request.getMinQuantity());
        }

        stockRepository.save(stock);

        MovementType movementType = stock.getQuantity() > oldQuantity ? MovementType.IN : MovementType.OUT;

        InventoryMovementEntity movement = InventoryMovementEntity.builder()
                .product(product)
                .fromWarehouse(warehouse)
                .toWarehouse(warehouse)
                .quantity(Math.abs(stock.getQuantity() - oldQuantity))
                .type(movementType)
                .build();

        movementRepository.save(movement);

        return stock;
    }
}
