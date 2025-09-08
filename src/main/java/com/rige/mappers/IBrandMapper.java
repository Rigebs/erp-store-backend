package com.rige.mappers;

import com.rige.dto.request.BrandRequest;
import com.rige.dto.response.BrandResponse;
import com.rige.entities.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandMapper {

    BrandResponse toResponse(BrandEntity entity);

    BrandEntity toEntity(BrandRequest request);
}
