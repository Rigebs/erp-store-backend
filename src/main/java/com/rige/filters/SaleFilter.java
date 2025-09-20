package com.rige.filters;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SaleFilter {
    private String query;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double minTotal;
    private Double maxTotal;
    private Boolean status;
    private Long customerId;
    private Long cashierId;
}
