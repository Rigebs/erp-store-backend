package com.rige.services;

import com.rige.dto.CategoryDto;
import com.rige.dto.request.CategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    void save(CategoryRequest categoryRequest);
    Page<CategoryDto> findAll(Long userId, Pageable pageable);
    Page<CategoryDto> findAllActive(Long userId, Pageable pageable);
    CategoryDto findById(Long id);
    void update(Long id, CategoryRequest categoryRequest);
    void delete(Long id);
    void toggleStatus(Long id);;
}