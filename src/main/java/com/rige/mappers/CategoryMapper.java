package com.rige.mappers;

import com.rige.dto.CategoryDto;
import com.rige.entities.CategoryEntity;
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

    public List<CategoryDto> toDtoList(List<CategoryEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}