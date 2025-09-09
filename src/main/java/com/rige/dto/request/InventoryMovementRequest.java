package com.rige.dto.request;

import com.rige.enums.MovementType;
import lombok.Data;

@Data
public class InventoryMovementRequest {
    private Long productId;
    private Long fromWarehouseId;
    private Long toWarehouseId;
    private int quantity;
    private MovementType type;
}