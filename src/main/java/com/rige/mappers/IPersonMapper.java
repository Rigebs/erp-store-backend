package com.rige.mappers;

import com.rige.dto.request.PersonRequest;
import com.rige.dto.response.PersonResponse;
import com.rige.entities.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPersonMapper {
    PersonResponse toResponse(PersonEntity entity);

    PersonEntity toEntity(PersonRequest request);
}
