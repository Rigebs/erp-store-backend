package com.rige.mappers;

import com.rige.dto.request.UnitMeasureRequest;
import com.rige.dto.response.UnitMeasureResponse;
import com.rige.entities.UnitMeasureEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUnitMeasureMapper {
    UnitMeasureResponse toResponse(UnitMeasureEntity unitMeasure);

    UnitMeasureEntity toEntity(UnitMeasureRequest request);
}