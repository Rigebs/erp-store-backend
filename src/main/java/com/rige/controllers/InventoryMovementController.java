package com.rige.controllers;

import com.rige.dto.request.InventoryMovementRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.InventoryMovementResponse;
import com.rige.enums.MovementType;
import com.rige.filters.MovementFilter;
import com.rige.services.IInventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/movements")
@RequiredArgsConstructor
public class InventoryMovementController {

    private final IInventoryMovementService movementService;

    @PostMapping
    public ResponseEntity<ApiResponse<InventoryMovementResponse>> createMovement(
            @RequestBody InventoryMovementRequest request
    ) {
        final InventoryMovementResponse movement = movementService.createMovement(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true,
                        "Movement created successfully",
                        movement
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<InventoryMovementResponse>>> getAllMovements(
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) Long fromWarehouseId,
            @RequestParam(required = false) Long toWarehouseId,
            @RequestParam(required = false) MovementType type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @PageableDefault Pageable pageable
    ) {
        var filter = MovementFilter.builder()
                .productId(productId)
                .fromWarehouseId(fromWarehouseId)
                .toWarehouseId(toWarehouseId)
                .type(type)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        final Page<InventoryMovementResponse> movements = movementService.getAllMovements(filter, pageable);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Movements retrieved successfully",
                        movements
                )
        );
    }
}