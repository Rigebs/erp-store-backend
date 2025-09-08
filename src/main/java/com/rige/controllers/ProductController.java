package com.rige.controllers;

import com.rige.dto.request.ProductRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.ProductResponse;
import com.rige.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody ProductRequest productRequest) {
        productService.save(productRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Product created successfully", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> findAll(
            @PageableDefault Pageable pageable) {
        Page<ProductResponse> result = productService.findAll(pageable);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Products retrieved successfully", result)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> findById(@PathVariable Long id) {
        var product = productService.findById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Product retrieved successfully", product)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody ProductRequest productRequest) {
        productService.update(id, productRequest);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Product updated successfully", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Product deleted successfully", null)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> toggleEnabled(@PathVariable Long id) {
        productService.toggleEnabled(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Product status updated successfully", null)
        );
    }
}