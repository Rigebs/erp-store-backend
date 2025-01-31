package com.rige.mappers;

import com.rige.dto.CategoryDto;
import com.rige.dto.request.CategoryRequest;
import com.rige.entities.CategoryEntity;
import com.rige.entities.UserEntity;
import com.rige.models.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
                .status(entity.isStatus())
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
                .status(entity.isStatus())
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
                .userEntity(UserEntity.builder().id(categoryRequest.getUserId()).build())
                .build();
    }

    public List<CategoryDto> toDtoList(List<CategoryEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}