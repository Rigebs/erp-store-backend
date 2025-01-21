package com.rige.dto.custom;

import com.rige.dto.SaleDetailDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class FullSaleDetailsDto {
    private LocalDateTime dateTime;
    private Double subtotal;
    private Double total;
    private Double tax;
    private Double discount;
    private boolean status;
    private String customer;
    private List<SaleDetailDto> saleDetails;
}
