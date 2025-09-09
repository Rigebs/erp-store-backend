package com.rige.mappers;

import com.rige.dto.request.StockRequest;
import com.rige.dto.response.StockResponse;
import com.rige.entities.StockEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStockMapper {

    StockResponse toResponse(StockEntity entity);

    StockEntity toEntity(StockRequest request);
}
