package com.rige.mappers;

import com.rige.dto.request.CategoryRequest;
import com.rige.dto.response.CategoryResponse;
import com.rige.entities.CategoryEntity;
import com.rige.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {
    CategoryResponse toResponse(CategoryEntity entity);

    CategoryEntity toEntity(CategoryRequest request);

    void updateFromRequest(CategoryRequest request, @MappingTarget Category entity);
}