package com.rige.controllers;

import com.rige.dto.LineDto;
import com.rige.dto.request.LineRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.services.ILineService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/from/{userId}")
    public ResponseEntity<Page<LineDto>> findAll(@PathVariable Long userId,
                                                 @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(lineService.findAll(userId, pageable));
    }

    @GetMapping("/from/{userId}/active")
    public ResponseEntity<Page<LineDto>> findAllActive(@PathVariable Long userId,
                                                       @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(lineService.findAllActive(userId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LineDto> findById(@PathVariable Long id) {
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