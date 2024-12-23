package com.rige.services;

import com.rige.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> findAllActive();
}
