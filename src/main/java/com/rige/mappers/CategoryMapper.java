package com.rige.mappers;

import com.rige.dto.CategoryDto;
import com.rige.dto.request.CategoryRequest;
import com.rige.entities.CategoryEntity;
import com.rige.entities.UserEntity;
import com.rige.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toDto(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return CategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.isEnabled())
                .flag(entity.isFlag())
                .build();
    }

    public Category toDomain(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.isEnabled())
                .flag(entity.isFlag())
        .build();
    }

    public CategoryEntity toDbo(CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            return null;
        }
        return CategoryEntity.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
    }

    public Page<CategoryDto> toDtoList(Page<CategoryEntity> entities) {
        return entities.map(this::toDto);
    }
}