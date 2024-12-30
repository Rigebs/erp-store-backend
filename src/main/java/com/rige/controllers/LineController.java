package com.rige.controllers;

import com.rige.dto.BrandDto;
import com.rige.dto.LineDto;
import com.rige.dto.request.LineRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.models.Line;
import com.rige.services.ILineService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/lines")
@AllArgsConstructor
public class LineController {

    private final ILineService lineService;

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody LineRequest lineRequest) {
        lineService.save(lineRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Línea creada"));
    }

    @GetMapping
    public ResponseEntity<List<LineDto>> findAll() {
        return ResponseEntity.ok(lineService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<LineDto>> findAllActive() {
        return ResponseEntity.ok(lineService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Line> findById(@PathVariable Long id) {
        return ResponseEntity.ok(lineService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody LineRequest lineRequest) {
        lineService.update(id, lineRequest);
        return ResponseEntity.ok(new ApiResponse("Línea actualizada"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        lineService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Línea eliminada"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> toggleStatus(@PathVariable Long id) {
        lineService.toggleStatus(id);
        return ResponseEntity.ok(new ApiResponse("Estado de la línea actualizado"));
    }
}
