package com.rige.services;

import com.rige.dto.request.InventoryMovementRequest;
import com.rige.dto.response.InventoryMovementResponse;
import com.rige.filters.MovementFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IInventoryMovementService {
    InventoryMovementResponse createMovement(InventoryMovementRequest movement);
    Page<InventoryMovementResponse> getAllMovements(MovementFilter filter, Pageable pageable);
}
