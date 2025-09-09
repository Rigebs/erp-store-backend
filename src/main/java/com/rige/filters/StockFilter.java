package com.rige.filters;

import lombok.*;

@Data
@Builder
public class StockFilter {
    private Long productId;
    private Long warehouseId;
    private Integer minQuantity;
    private Integer maxQuantity;
    private Boolean belowMin;
}
