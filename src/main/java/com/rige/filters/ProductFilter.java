package com.rige.filters;

import lombok.Data;

@Data
public class ProductFilter {
    private String query;
    private Long categoryId;
    private Long brandId;
    private Long supplierId;
    private Long unitMeasureId;
    private Long lineId;
    private Double minPrice;
    private Double maxPrice;
    private Boolean enabled;
    private Boolean flag;
}