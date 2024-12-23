package com.rige.controllers;

import com.rige.dto.SupplierDto;
import com.rige.services.ISupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@AllArgsConstructor
public class SupplierController {
    private final ISupplierService iSupplierService;

    @GetMapping
    public ResponseEntity<List<SupplierDto>> findAll() {
        return ResponseEntity.ok(iSupplierService.findAllActive());
    }
}