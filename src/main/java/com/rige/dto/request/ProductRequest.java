package com.rige.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private int quantity;
    private double purchasePrice;
    private double salePrice;
    private Long brandId;
    private Long unitMeasureId;
    private Long categoryId;
    private Long lineId;
    private Long supplierId;
}