package com.rige.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryMovementResponse {
    private Long id;
    private ProductResponse product;
    private WarehouseResponse fromWarehouse;
    private WarehouseResponse toWarehouse;
    private int quantity;
    private String type;
    private LocalDateTime date;
}
