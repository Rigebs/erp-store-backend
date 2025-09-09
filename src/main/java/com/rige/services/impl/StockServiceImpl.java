package com.rige.services.impl;

import com.rige.dto.request.StockRequest;
import com.rige.dto.response.StockResponse;
import com.rige.filters.StockFilter;
import com.rige.mappers.IStockMapper;
import com.rige.repositories.IStockRepository;
import com.rige.services.IStockService;
import com.rige.specifications.StockSpecification;
import com.rige.strategy.StockStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements IStockService {

    private final StockStrategyFactory strategyFactory;
    private final IStockMapper stockMapper;
    private final IStockRepository stockRepository;

    @Override
    public StockResponse handleStock(StockRequest request) {
        var strategy = strategyFactory.getStrategy(request.getAction().name());
        var stockEntity = strategy.execute(request);

        return stockMapper.toResponse(stockEntity);
    }

    @Override
    public Page<StockResponse> getAllStock(StockFilter filter, Pageable pageable) {
        var spec = StockSpecification.build(filter);
        var stocks = stockRepository.findAll(spec, pageable);
        return stocks.map(stockMapper::toResponse);
    }
}