package com.rige.controllers;

import com.rige.dto.BrandDto;
import com.rige.services.IBrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brands")
@AllArgsConstructor
public class BrandController {
    private final IBrandService iBrandService;

    @GetMapping
    public ResponseEntity<List<BrandDto>> findAll() {
        return ResponseEntity.ok(iBrandService.findAllActive());
    }
}
