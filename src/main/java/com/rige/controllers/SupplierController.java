package com.rige.controllers;

import com.rige.dto.BrandDto;
import com.rige.dto.SupplierDto;
import com.rige.dto.request.SupplierRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.entities.SupplierEntity;
import com.rige.models.Supplier;
import com.rige.services.ISupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/suppliers")
@AllArgsConstructor
public class SupplierController {

    private final ISupplierService supplierService;

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody SupplierRequest supplierRequest) {
        supplierService.save(supplierRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Proveedor creado"));
    }

    @GetMapping
    public ResponseEntity<List<SupplierDto>> findAll() {
        return ResponseEntity.ok(supplierService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<SupplierDto>> findAllActive() {
        return ResponseEntity.ok(supplierService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> findById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody SupplierRequest supplierRequest) {
        supplierService.update(id, supplierRequest);
        return ResponseEntity.ok(new ApiResponse("Proveedor actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Proveedor eliminado"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> toggleStatus(@PathVariable Long id) {
        supplierService.toggleStatus(id);
        return ResponseEntity.ok(new ApiResponse("Estado del proveedor actualizado"));
    }
}
