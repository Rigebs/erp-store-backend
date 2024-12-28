package com.rige.services;

import com.rige.dto.CategoryDto;
import com.rige.dto.request.CategoryRequest;
import com.rige.models.Category;

import java.util.List;

public interface ICategoryService {
    void save(CategoryRequest categoryRequest);
    List<CategoryDto> findAll();
    List<CategoryDto> findAllActive();
    Category findById(Long id);
    void update(Long id, CategoryRequest categoryRequest);
    void delete(Long id);
    void toggleStatus(Long id);;
}