package com.rige.controllers;

import com.rige.dto.SaleDto;
import com.rige.dto.custom.FullSaleDetailsDto;
import com.rige.dto.request.SaleRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.services.ISaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/sales")
@AllArgsConstructor
public class SaleController {

    private final ISaleService iSaleService;

    @GetMapping("/from/{userId}")
    public ResponseEntity<List<SaleDto>> findAll(@PathVariable Long userId) {
        return ResponseEntity.ok(iSaleService.findAllByUser(userId));
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<FullSaleDetailsDto> findById(@PathVariable Long saleId) {
        return ResponseEntity.ok(iSaleService.findById(saleId));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody SaleRequest saleRequest) {
        iSaleService.save(saleRequest);
        return new ResponseEntity<>(new ApiResponse("Venta guardada correctamente"), HttpStatus.CREATED);
    }
}
