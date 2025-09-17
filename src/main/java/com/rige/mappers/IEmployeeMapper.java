package com.rige.mappers;

import com.rige.dto.request.EmployeeRequest;
import com.rige.dto.response.EmployeeResponse;
import com.rige.entities.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IEmployeeMapper {
    EmployeeResponse toResponse(EmployeeEntity entity);

    EmployeeEntity toEntity(EmployeeRequest request);

    void updateFromRequest(EmployeeRequest request, @MappingTarget EmployeeEntity entity);
}