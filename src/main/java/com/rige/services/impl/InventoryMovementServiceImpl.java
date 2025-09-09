package com.rige.services.impl;

import com.rige.dto.request.InventoryMovementRequest;
import com.rige.dto.response.InventoryMovementResponse;
import com.rige.filters.MovementFilter;
import com.rige.mappers.IInventoryMovementMapper;
import com.rige.repositories.IInventoryMovementRepository;
import com.rige.services.IInventoryMovementService;
import com.rige.specifications.InventoryMovementSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryMovementServiceImpl implements IInventoryMovementService {

    private final IInventoryMovementRepository movementRepository;
    private final IInventoryMovementMapper inventoryMovementMapper;

    @Override
    public InventoryMovementResponse createMovement(InventoryMovementRequest movement) {
        var entity = inventoryMovementMapper.toEntity(movement);
        var saved = movementRepository.save(entity);
        return inventoryMovementMapper.toResponse(saved);
    }

    @Override
    public Page<InventoryMovementResponse> getAllMovements(MovementFilter filter, Pageable pageable) {
        var spec = InventoryMovementSpecification.build(filter);
        var movements = movementRepository.findAll(spec, pageable);
        return movements.map(inventoryMovementMapper::toResponse);
    }
}
