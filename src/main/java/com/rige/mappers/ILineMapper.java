package com.rige.mappers;

import com.rige.dto.request.LineRequest;
import com.rige.dto.response.LineResponse;
import com.rige.entities.LineEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ILineMapper {
    LineResponse toResponse(LineEntity entity);

    LineEntity toEntity(LineRequest request);
}