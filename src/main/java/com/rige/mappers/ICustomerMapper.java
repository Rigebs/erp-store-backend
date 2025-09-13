package com.rige.mappers;

import com.rige.dto.request.CustomerRequest;
import com.rige.dto.response.CustomerResponse;
import com.rige.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
    CustomerResponse toResponse(CustomerEntity entity);

    CustomerEntity toEntity(CustomerRequest request);

    void updateFromRequest(CustomerRequest request, @MappingTarget CustomerEntity entity);
}
