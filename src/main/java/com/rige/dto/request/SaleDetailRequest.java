package com.rige.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleDetailRequest {
    private Integer quantity;
    private Double price;
    private Double subtotal;
    private Long productId;
}
