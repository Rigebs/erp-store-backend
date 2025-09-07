package com.rige.controllers;

import com.rige.dto.SaleDto;
import com.rige.dto.custom.FullSaleDetailsDto;
import com.rige.dto.request.SaleRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.services.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final ISaleService saleService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SaleDto>>> findAll(@PathVariable Long userId) {
        List<SaleDto> result = saleService.findAllByUser(userId);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Sales retrieved successfully", result)
        );
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<ApiResponse<FullSaleDetailsDto>> findById(@PathVariable Long saleId) {
        FullSaleDetailsDto sale = saleService.findById(saleId);
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