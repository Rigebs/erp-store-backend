package com.rige.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleDetailDto {
    private Integer quantity;
    private Double price;
    private Double subtotal;
    private String productName;
}
