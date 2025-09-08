package com.rige.dto.response;

import lombok.Data;

@Data
public class SaleDetailResponse {
    private int quantity;
    private double price;
    private double subtotal;
    private ProductResponse product;
}