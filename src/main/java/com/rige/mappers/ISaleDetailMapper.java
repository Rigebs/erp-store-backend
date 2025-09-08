package com.rige.mappers;

import com.rige.dto.response.SaleDetailResponse;
import com.rige.entities.SaleDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ISaleDetailMapper {

    @Mapping(target = "product.category", ignore = true)
    @Mapping(target = "product.supplier", ignore = true)
    @Mapping(target = "product.line", ignore = true)
    @Mapping(target = "product.unitMeasure", ignore = true)
    @Mapping(target = "product.brand", ignore = true)
    SaleDetailResponse toResponse(SaleDetailEntity saleDetail);
}
