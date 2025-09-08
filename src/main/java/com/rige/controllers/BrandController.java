package com.rige.controllers;

import com.rige.dto.request.BrandRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.BrandResponse;
import com.rige.services.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final IBrandService brandService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody BrandRequest brandRequest) {
        brandService.save(brandRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Brand created successfully", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<BrandResponse>>> findAll(
            @PageableDefault Pageable pageable) {

        Page<BrandResponse> result = brandService.findAll(pageable);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Brands retrieved successfully", result)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BrandResponse>> findById(@PathVariable Long id) {
        BrandResponse brand = brandService.findById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Brand retrieved successfully", brand)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody BrandRequest brandRequest) {
        brandService.update(id, brandRequest);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Brand updated successfully", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        brandService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Brand deleted successfully", null)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> toggleStatus(@PathVariable Long id) {
        brandService.toggleStatus(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Brand status updated successfully", null)
        );
    }
}