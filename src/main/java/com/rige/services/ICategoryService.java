package com.rige.services;

import com.rige.dto.request.CategoryRequest;
import com.rige.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    void save(CategoryRequest categoryRequest);
    Page<CategoryResponse> findAll(Pageable pageable);
    CategoryResponse findById(Long id);
    void update(Long id, CategoryRequest categoryRequest);
    void delete(Long id);
    void toggleEnabled(Long id);
}