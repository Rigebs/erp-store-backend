package com.rige.filters;

import com.rige.enums.MovementType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MovementFilter {
    private Long productId;
    private Long fromWarehouseId;
    private Long toWarehouseId;
    private MovementType type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
