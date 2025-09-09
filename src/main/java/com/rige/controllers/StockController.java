package com.rige.controllers;

import com.rige.dto.request.StockRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.StockResponse;
import com.rige.filters.StockFilter;
import com.rige.services.IStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final IStockService stockService;

    @PostMapping("/manage")
    public ResponseEntity<ApiResponse<StockResponse>> manageStock(@RequestBody StockRequest request) {
        final StockResponse response = stockService.handleStock(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Stock managed successfully",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<StockResponse>>> getAllStock(
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) Long warehouseId,
            @RequestParam(required = false) Integer minQuantity,
            @RequestParam(required = false) Integer maxQuantity,
            @RequestParam(required = false) Boolean belowMin,
            @PageableDefault Pageable pageable
    ) {
        final StockFilter filter = StockFilter.builder()
                .productId(productId)
                .warehouseId(warehouseId)
                .minQuantity(minQuantity)
                .maxQuantity(maxQuantity)
                .belowMin(belowMin)
                .build();

        final Page<StockResponse> stocks = stockService.getAllStock(filter, pageable);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Stock retrieved successfully",
                        stocks
                )
        );
    }
}