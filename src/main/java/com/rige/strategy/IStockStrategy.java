package com.rige.strategy;

import com.rige.dto.request.StockRequest;
import com.rige.entities.StockEntity;

public interface IStockStrategy {

    StockEntity execute(StockRequest request);
}
