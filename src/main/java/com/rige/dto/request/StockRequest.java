package com.rige.dto.request;

import com.rige.enums.StockAction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockRequest {
    private Long productId;
    private Long warehouseId;
    private Long targetWarehouseId;
    private int quantity;
    private int minQuantity;
    private StockAction action;
}
