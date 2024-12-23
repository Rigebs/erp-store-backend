package com.rige.controllers;

import com.rige.dto.ProductDto;
import com.rige.dto.request.ProductRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.services.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody ProductRequest productRequest) {
        productService.save(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Producto creado"));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> products = productService.findAllActive();
        return ResponseEntity.ok(products);
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
}