package com.rige.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class StockStrategyFactory {

    private final Map<String, IStockStrategy> strategies;

    public IStockStrategy getStrategy(String action) {
        IStockStrategy strategy = strategies.get(action.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for action: " + action);
        }
        return strategy;
    }
}