package com.rige.mappers;

import com.rige.dto.request.SupplierRequest;
import com.rige.dto.response.SupplierResponse;
import com.rige.entities.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplierMapper {
    SupplierResponse toResponse(SupplierEntity supplier);

    SupplierEntity toEntity(SupplierRequest request);
}