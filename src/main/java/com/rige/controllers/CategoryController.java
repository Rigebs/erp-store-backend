package com.rige.controllers;

import com.rige.dto.CategoryDto;
import com.rige.dto.request.CategoryRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody CategoryRequest categoryRequest) {
        categoryService.save(categoryRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Category created successfully", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CategoryDto>>> findAll(
            @PathVariable Long userId,
            @PageableDefault Pageable pageable) {
        Page<CategoryDto> result = categoryService.findAll(userId, pageable);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Categories retrieved successfully", result)
        );
    }

    @GetMapping("/from/{userId}/active")
    public ResponseEntity<ApiResponse<Page<CategoryDto>>> findAllActive(
            @PathVariable Long userId,
            @PageableDefault Pageable pageable) {
        Page<CategoryDto> result = categoryService.findAllActive(userId, pageable);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Active categories retrieved successfully", result)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> findById(@PathVariable Long id) {
        CategoryDto category = categoryService.findById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Category retrieved successfully", category)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody CategoryRequest categoryRequest) {
        categoryService.update(id, categoryRequest);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Category updated successfully", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Category deleted successfully", null)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> toggleStatus(@PathVariable Long id) {
        categoryService.toggleStatus(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Category status updated successfully", null)
        );
    }
}