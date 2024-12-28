package com.rige.mappers;

import com.rige.dto.BrandDto;
import com.rige.dto.request.BrandRequest;
import com.rige.entities.BrandEntity;
import com.rige.models.Brand;
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

    public Brand toDomain(BrandEntity entity) {
        if (entity == null) {
            return null;
        }
        return Brand.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.isStatus())
                .flag(entity.isFlag())
                .build();
    }

    public BrandEntity toDbo(BrandRequest brandRequest) {
        if (brandRequest == null) {
            return null;
        }
        return BrandEntity.builder()
                .name(brandRequest.getName())
                .description(brandRequest.getDescription())
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