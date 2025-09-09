package com.rige.mappers;

import com.rige.dto.request.InventoryMovementRequest;
import com.rige.dto.response.InventoryMovementResponse;
import com.rige.entities.InventoryMovementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IInventoryMovementMapper {

    InventoryMovementResponse toResponse(InventoryMovementEntity entity);

    InventoryMovementEntity toEntity(InventoryMovementRequest request);
}
