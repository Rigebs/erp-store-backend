package com.rige.mappers;

import com.rige.dto.request.SaleRequest;
import com.rige.dto.response.SaleResponse;
import com.rige.entities.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ISaleDetailMapper.class })
public interface ISaleMapper {

    SaleResponse toResponse(SaleEntity sale);

    @Mapping(source = "customerId", target = "customer.id")
    SaleEntity toEntity(SaleRequest sale);
}
