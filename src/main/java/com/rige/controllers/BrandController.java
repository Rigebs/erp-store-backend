package com.rige.controllers;

import com.rige.dto.BrandDto;
import com.rige.dto.request.BrandRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.models.Brand;
import com.rige.services.IBrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/brands")
@AllArgsConstructor
public class BrandController {

    private final IBrandService brandService;

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody BrandRequest brandRequest) {
        brandService.save(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Marca creada"));
    }

    @GetMapping
    public ResponseEntity<List<BrandDto>> findAll() {
        return ResponseEntity.ok(brandService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<BrandDto>> findAllActive() {
        return ResponseEntity.ok(brandService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> findById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody BrandRequest brandRequest) {
        brandService.update(id, brandRequest);
        return ResponseEntity.ok(new ApiResponse("Marca actualizada"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        brandService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Marca eliminada"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> toggleStatus(@PathVariable Long id) {
        brandService.toggleStatus(id);
        return ResponseEntity.ok(new ApiResponse("Estado de la marca actualizado"));
    }
}
