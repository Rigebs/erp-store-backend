package com.rige.dto.response;

import lombok.Data;

@Data
public class StockResponse {
    private Long id;
    private ProductResponse product;
    private WarehouseResponse warehouse;
    private int quantity;
    private int minQuantity;
}
