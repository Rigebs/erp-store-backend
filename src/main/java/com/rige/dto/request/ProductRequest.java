package com.rige.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private int quantity;
    private double purchasePrice;
    private double salePrice;
    private String imageUrl;
    private Long brandId;
    private Long unitMeasureId;
    private Long categoryId;
    private Long lineId;
    private Long supplierId;
}