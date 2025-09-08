package com.rige.controllers;

import com.rige.dto.request.UnitMeasureRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.UnitMeasureResponse;
import com.rige.services.IUnitMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/units-measure")
@RequiredArgsConstructor
public class UnitMeasureController {

    private final IUnitMeasureService unitMeasureService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody UnitMeasureRequest unitMeasureRequest) {
        unitMeasureService.save(unitMeasureRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Unit measure created successfully", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<UnitMeasureResponse>>> findAll(
            @PageableDefault Pageable pageable) {
        Page<UnitMeasureResponse> result = unitMeasureService.findAll(pageable);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Units measure retrieved successfully", result)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UnitMeasureResponse>> findById(@PathVariable Long id) {
        UnitMeasureResponse unitMeasure = unitMeasureService.findById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Unit measure retrieved successfully", unitMeasure)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody UnitMeasureRequest unitMeasureRequest) {
        unitMeasureService.update(id, unitMeasureRequest);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Unit measure updated successfully", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        unitMeasureService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Unit measure deleted successfully", null)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> toggleStatus(@PathVariable Long id) {
        unitMeasureService.toggleEnabled(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Unit measure status updated successfully", null)
        );
    }
}