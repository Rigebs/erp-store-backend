package com.rige.controllers;

import com.rige.dto.request.SaleRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.services.ISaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users/sales")
@AllArgsConstructor
public class SaleController {

    private final ISaleService iSaleService;

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody SaleRequest saleRequest) {
        iSaleService.save(saleRequest);
        return new ResponseEntity<>(new ApiResponse("Sale saved successfully"), HttpStatus.CREATED);
    }
}
