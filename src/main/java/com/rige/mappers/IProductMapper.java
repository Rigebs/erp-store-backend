package com.rige.mappers;

import com.rige.dto.request.ProductRequest;
import com.rige.dto.response.ProductResponse;
import com.rige.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    ProductResponse toResponse(ProductEntity entity);

    ProductEntity toEntity(ProductRequest request);

    void updateFromRequest(ProductRequest request, @MappingTarget ProductEntity entity);
}