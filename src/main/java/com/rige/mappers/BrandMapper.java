package com.rige.mappers;

import com.rige.dto.BrandDto;
import com.rige.entities.BrandEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BrandMapper {

    public BrandDto toDto(BrandEntity entity) {
        if (entity == null) {
            return null;
        }
        return BrandDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.isStatus())
                .flag(entity.isFlag())
                .build();
    }

    public List<BrandDto> toDtoList(List<BrandEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}