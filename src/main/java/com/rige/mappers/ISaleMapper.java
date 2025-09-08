package com.rige.mappers;

import com.rige.dto.request.SaleRequest;
import com.rige.dto.response.SaleResponse;
import com.rige.entities.SaleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ISaleDetailMapper.class })
public interface ISaleMapper {

    SaleResponse toResponse(SaleEntity sale);

    SaleEntity toEntity(SaleRequest sale);
}
