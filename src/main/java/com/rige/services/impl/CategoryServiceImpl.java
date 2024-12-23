package com.rige.services.impl;

import com.rige.dto.CategoryDto;
import com.rige.mappers.CategoryMapper;
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
    public List<CategoryDto> findAllActive() {
        return categoryMapper.toDtoList(iCategoryRepository.findByFlag(true));
    }
}
