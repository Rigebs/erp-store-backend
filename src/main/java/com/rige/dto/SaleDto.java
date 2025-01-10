package com.rige.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SaleDto {
    private Long id;
    private LocalDateTime dateTime;
    private Double subtotal;
    private Double total;
    private Double tax;
    private Double discount;
    private boolean status;
    private String customer;
    private String cashier;
    private List<SaleDetailDto> saleDetails;
}
