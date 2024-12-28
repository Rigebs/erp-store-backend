package com.rige.services.impl;

import com.rige.dto.CategoryDto;
import com.rige.dto.request.CategoryRequest;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.CategoryMapper;
import com.rige.models.Category;
import com.rige.repositories.ICategoryRepository;
import com.rige.services.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository iCategoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void save(CategoryRequest categoryRequest) {
        var category = categoryMapper.toDbo(categoryRequest);
        category.setStatus(true);
        category.setFlag(true);
        iCategoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryMapper.toDtoList(iCategoryRepository.findByFlag(true));
    }

    @Override
    public List<CategoryDto> findAllActive() {
        return categoryMapper.toDtoList(iCategoryRepository.findByStatusAndFlag(true, true));
    }

    @Override
    public Category findById(Long id) {
        return categoryMapper.toDomain(iCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id)));
    }

    @Override
    public void update(Long id, CategoryRequest categoryRequest) {
        var existingCategory = iCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        existingCategory.setName(categoryRequest.getName());
        existingCategory.setDescription(categoryRequest.getDescription());

        iCategoryRepository.save(existingCategory);
    }

    @Override
    public void delete(Long id) {
        var existingCategory = iCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        existingCategory.setFlag(false);
        iCategoryRepository.save(existingCategory);
    }

    @Override
    public void toggleStatus(Long id) {
        var existingCategory = iCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        existingCategory.setStatus(!existingCategory.isStatus());
        iCategoryRepository.save(existingCategory);
    }
}
