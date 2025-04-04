package com.rige.mappers;

import com.rige.dto.BrandDto;
import com.rige.dto.request.BrandRequest;
import com.rige.entities.BrandEntity;
import com.rige.entities.UserEntity;
import com.rige.models.Brand;
import org.springframework.data.domain.Page;
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
                .userEntity(UserEntity.builder().id(brandRequest.getUserId()).build())
                .build();
    }

    public Page<BrandDto> toDtoList(Page<BrandEntity> entities) {
        return entities.map(this::toDto);
    }
}