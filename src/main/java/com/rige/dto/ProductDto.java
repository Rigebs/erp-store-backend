package com.rige.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double purchasePrice;
    private double salePrice;
    private boolean status;
    private boolean flag;
    private String brandName;
    private String categoryName;
    private String unitMeasureName;
    private String lineName;
    private String supplierName;
}
