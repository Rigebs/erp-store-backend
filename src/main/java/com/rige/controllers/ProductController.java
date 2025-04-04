package com.rige.controllers;

import com.rige.dto.ProductDto;
import com.rige.dto.request.ProductRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.models.Product;
import com.rige.services.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users/products")
@AllArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody ProductRequest productRequest) {
        productService.save(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Producto creado"));
    }

    @GetMapping("/from/{userId}")
    public ResponseEntity<Page<ProductDto>> findAll(@PageableDefault Pageable pageable,
                                                    @PathVariable Long userId) {
        return ResponseEntity.ok(productService.findAll(userId, pageable));
    }

    @GetMapping("/from/{userId}/active")
    public ResponseEntity<Page<ProductDto>> findAllActive(@PathVariable Long userId,
                                                          @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(productService.findAllActive(userId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        productService.update(id, productRequest);
        return ResponseEntity.ok(new ApiResponse("Producto actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Producto eliminado"));
    }

    @DeleteMapping("/{tableName}/{id}")
    public ResponseEntity<ApiResponse> deleteRelationships(
            @PathVariable("tableName") String tableName,
            @PathVariable("id") Long id) {
        productService.deleteRelationships(id, tableName);
        return ResponseEntity.ok(new ApiResponse("Relación eliminada correctamente"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> toggleStatus(@PathVariable Long id) {
        productService.toggleStatus(id);
        return ResponseEntity.ok(new ApiResponse("Estado actualizado"));
    }
}