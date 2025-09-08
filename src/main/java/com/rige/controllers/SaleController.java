package com.rige.controllers;

import com.rige.dto.request.SaleRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.SaleResponse;
import com.rige.services.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final ISaleService saleService;
//
    @GetMapping
    public ResponseEntity<ApiResponse<Page<SaleResponse>>> findAll(@PageableDefault Pageable pageable) {
        Page<SaleResponse> result = saleService.findAll(pageable);
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