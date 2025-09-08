package com.rige.services.impl;

import com.rige.dto.request.CategoryRequest;
import com.rige.dto.response.CategoryResponse;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.ICategoryMapper;
import com.rige.repositories.ICategoryRepository;
import com.rige.services.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;

    @Override
    public void save(CategoryRequest categoryRequest) {
        var category = categoryMapper.toEntity(categoryRequest);
        category.setEnabled(true);
        category.setFlag(true);
        categoryRepository.save(category);
    }

    @Override
    public Page<CategoryResponse> findAll(Pageable pageable) {
        var categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toResponse);
    }

    @Override
    public CategoryResponse findById(Long id) {
        return categoryMapper.toResponse(categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id)));
    }

    @Override
    public void update(Long id, CategoryRequest categoryRequest) {
        var existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        existingCategory.setName(categoryRequest.getName());
        existingCategory.setDescription(categoryRequest.getDescription());

        categoryRepository.save(existingCategory);
    }

    @Override
    public void delete(Long id) {
        var existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        existingCategory.setFlag(false);
        categoryRepository.save(existingCategory);
    }

    @Override
    public void toggleEnabled(Long id) {
        var existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        existingCategory.setEnabled(!existingCategory.isEnabled());
        categoryRepository.save(existingCategory);
    }
}
