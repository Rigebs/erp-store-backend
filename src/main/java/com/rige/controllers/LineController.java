package com.rige.controllers;

import com.rige.dto.LineDto;
import com.rige.dto.request.LineRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.services.ILineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lines")
@RequiredArgsConstructor
public class LineController {

    private final ILineService lineService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody LineRequest lineRequest) {
        lineService.save(lineRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Line created successfully", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<LineDto>>> findAll(
            @PathVariable Long userId,
            @PageableDefault Pageable pageable) {
        Page<LineDto> result = lineService.findAll(userId, pageable);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lines retrieved successfully", result)
        );
    }

    @GetMapping("/from/{userId}/active")
    public ResponseEntity<ApiResponse<Page<LineDto>>> findAllActive(
            @PathVariable Long userId,
            @PageableDefault Pageable pageable) {
        Page<LineDto> result = lineService.findAllActive(userId, pageable);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Active lines retrieved successfully", result)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LineDto>> findById(@PathVariable Long id) {
        LineDto line = lineService.findById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Line retrieved successfully", line)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody LineRequest lineRequest) {
        lineService.update(id, lineRequest);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Line updated successfully", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        lineService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Line deleted successfully", null)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> toggleStatus(@PathVariable Long id) {
        lineService.toggleStatus(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Line status updated successfully", null)
        );
    }
}