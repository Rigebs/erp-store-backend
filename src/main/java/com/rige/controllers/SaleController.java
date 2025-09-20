package com.rige.controllers;

import com.rige.dto.request.SaleRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.SaleResponse;
import com.rige.filters.SaleFilter;
import com.rige.services.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final ISaleService saleService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SaleResponse>>> findAll(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) Double minTotal,
            @RequestParam(required = false) Double maxTotal,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long cashierId,
            @PageableDefault Pageable pageable
    ) {
        SaleFilter filter = new SaleFilter();
        filter.setQuery(query);
        filter.setStartDate(startDate);
        filter.setEndDate(endDate);
        filter.setMinTotal(minTotal);
        filter.setMaxTotal(maxTotal);
        filter.setStatus(status);
        filter.setCustomerId(customerId);
        filter.setCashierId(cashierId);

        Page<SaleResponse> result = saleService.findAll(filter, pageable);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Sales retrieved successfully", result)
        );
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<ApiResponse<SaleResponse>> findById(@PathVariable Long saleId) {
        SaleResponse sale = saleService.findById(saleId);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Sale retrieved successfully", sale)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody SaleRequest saleRequest) {
        saleService.save(saleRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Sale created successfully", null));
    }
}