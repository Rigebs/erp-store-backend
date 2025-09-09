package com.rige.mappers;

import com.rige.dto.response.WarehouseResponse;
import com.rige.entities.WarehouseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IWarehouseMapper {

    WarehouseResponse toResponse(WarehouseEntity entity);

    WarehouseEntity toEntity(WarehouseEntity request);
}
