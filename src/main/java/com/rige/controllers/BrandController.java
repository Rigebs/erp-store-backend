package com.rige.controllers;

import com.rige.dto.BrandDto;
import com.rige.dto.request.BrandRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.services.IBrandService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/from/{userId}")
    public ResponseEntity<Page<BrandDto>> findAll(@PathVariable Long userId,
                                                  @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(brandService.findAll(userId, pageable));
    }

    @GetMapping("/from/{userId}/active")
    public ResponseEntity<Page<BrandDto>> findAllActive(@PathVariable Long userId,
                                                        @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(brandService.findAllActive(userId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> findById(@PathVariable Long id) {
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