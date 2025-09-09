package com.rige.services;

import com.rige.dto.request.StockRequest;
import com.rige.dto.response.StockResponse;
import com.rige.filters.StockFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStockService {
    StockResponse handleStock(StockRequest request);
    Page<StockResponse> getAllStock(StockFilter filter, Pageable pageable);
}
