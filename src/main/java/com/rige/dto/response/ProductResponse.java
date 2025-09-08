package com.rige.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double purchasePrice;
    private double salePrice;
    private String imageUrl;
    private boolean enabled;
    private boolean flag;
    private BrandResponse brand;
    private CategoryResponse category;
    private UnitMeasureResponse unitMeasure;
    private LineResponse line;
    private SupplierResponse supplier;
}
