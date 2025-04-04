package com.rige.controllers;

import com.rige.dto.CategoryDto;
import com.rige.dto.request.CategoryRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.services.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/categories")
@AllArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody CategoryRequest categoryRequest) {
        categoryService.save(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Categoría creada"));
    }

    @GetMapping("/from/{userId}")
    public ResponseEntity<Page<CategoryDto>> findAll(@PathVariable Long userId,
                                                     @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(categoryService.findAll(userId, pageable));
    }

    @GetMapping("/from/{userId}/active")
    public ResponseEntity<Page<CategoryDto>> findAllActive(@PathVariable Long userId,
                                                           @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(categoryService.findAllActive(userId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        categoryService.update(id, categoryRequest);
        return ResponseEntity.ok(new ApiResponse("Categoría actualizada"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Categoría eliminada"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> toggleStatus(@PathVariable Long id) {
        categoryService.toggleStatus(id);
        return ResponseEntity.ok(new ApiResponse("Estado de la categoría actualizado"));
    }
}