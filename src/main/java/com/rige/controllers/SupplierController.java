package com.rige.controllers;

import com.rige.dto.request.SupplierRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.SupplierResponse;
import com.rige.services.ISupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final ISupplierService supplierService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody SupplierRequest supplierRequest) {
        supplierService.save(supplierRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Supplier created successfully", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SupplierResponse>>> findAll(
            @PageableDefault Pageable pageable) {
        Page<SupplierResponse> result = supplierService.findAll(pageable);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Suppliers retrieved successfully", result)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponse>> findById(@PathVariable Long id) {
        SupplierResponse supplier = supplierService.findById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Supplier retrieved successfully", supplier)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody SupplierRequest supplierRequest) {
        supplierService.update(id, supplierRequest);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Supplier updated successfully", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Supplier deleted successfully", null)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> toggleStatus(@PathVariable Long id) {
        supplierService.toggleEnabled(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Supplier status updated successfully", null)
        );
    }
}