package com.rige.controllers;

import com.rige.dto.UnitMeasureDto;
import com.rige.dto.request.UnitMeasureRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.models.UnitMeasure;
import com.rige.services.IUnitMeasureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/units-measure")
@AllArgsConstructor
public class UnitMeasureController {

    private final IUnitMeasureService unitMeasureService;

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody UnitMeasureRequest unitMeasureRequest) {
        unitMeasureService.save(unitMeasureRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Unidad de medida creada"));
    }

    @GetMapping("/from/{userId}")
    public ResponseEntity<List<UnitMeasureDto>> findAll(@PathVariable Long userId) {
        return ResponseEntity.ok(unitMeasureService.findAll(userId));
    }

    @GetMapping("/from/{userId}/active")
    public ResponseEntity<List<UnitMeasureDto>> findAllActive(@PathVariable Long userId) {
        return ResponseEntity.ok(unitMeasureService.findAllActive(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitMeasure> findById(@PathVariable Long id) {
        return ResponseEntity.ok(unitMeasureService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody UnitMeasureRequest unitMeasureRequest) {
        unitMeasureService.update(id, unitMeasureRequest);
        return ResponseEntity.ok(new ApiResponse("Unidad de medida actualizada"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        unitMeasureService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Unidad de medida eliminada"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> toggleStatus(@PathVariable Long id) {
        unitMeasureService.toggleStatus(id);
        return ResponseEntity.ok(new ApiResponse("Estado de la unidad de medida actualizado"));
    }
}