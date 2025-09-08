package com.rige.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SaleResponse {
    private Long id;
    private LocalDateTime dateTime;
    private Double subtotal;
    private Double total;
    private Double tax;
    private Double discount;
    private boolean enabled;
    private List<SaleDetailResponse> saleDetails;
}
