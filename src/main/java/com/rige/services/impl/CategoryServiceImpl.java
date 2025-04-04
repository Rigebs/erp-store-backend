package com.rige.services.impl;

import com.rige.dto.CategoryDto;
import com.rige.dto.request.CategoryRequest;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.CategoryMapper;
import com.rige.repositories.ICategoryRepository;
import com.rige.services.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<CategoryDto> findAll(Long userId, Pageable pageable) {
        return categoryMapper.toDtoList(iCategoryRepository.findByFlagAndUserEntity_Id(true, userId, pageable));
    }

    @Override
    public Page<CategoryDto> findAllActive(Long userId, Pageable pageable) {
        return categoryMapper.toDtoList(iCategoryRepository.findByFlagAndStatusAndUserEntity_Id(true, true, userId, pageable));
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryMapper.toDto(iCategoryRepository.findById(id)
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
