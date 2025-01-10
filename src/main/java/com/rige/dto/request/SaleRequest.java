package com.rige.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SaleRequest {
    private Double subtotal;
    private Double total;
    private Double tax;
    private Double discount;
    private boolean status;
    private Long customerId;
    private Long cashierId;
    private List<SaleDetailRequest> saleDetails;
}
