package com.rige.mappers;

import com.rige.dto.UnitMeasureDto;
import com.rige.dto.request.UnitMeasureRequest;
import com.rige.entities.UnitMeasureEntity;
import com.rige.entities.UserEntity;
import com.rige.models.UnitMeasure;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UnitMeasureMapper {

    public UnitMeasureDto toDto(UnitMeasureEntity entity) {
        if (entity == null) {
            return null;
        }
        return UnitMeasureDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .abbreviation(entity.getAbbreviation())
                .description(entity.getDescription())
                .status(entity.isStatus())
                .flag(entity.isFlag())
                .build();
    }

    public UnitMeasureEntity toDbo(UnitMeasureRequest unitMeasureRequest) {
        if (unitMeasureRequest == null) {
            return null;
        }
        return UnitMeasureEntity.builder()
                .name(unitMeasureRequest.getName())
                .abbreviation(unitMeasureRequest.getAbbreviation())
                .description(unitMeasureRequest.getDescription())
                .userEntity(UserEntity.builder().id(unitMeasureRequest.getUserId()).build())
                .build();
    }

    public UnitMeasure toDomain(UnitMeasureEntity entity) {
        if (entity == null) {
            return null;
        }
        return UnitMeasure.builder()
                .id(entity.getId())
                .name(entity.getName())
                .abbreviation(entity.getAbbreviation())
                .description(entity.getDescription())
                .status(entity.isStatus())
                .flag(entity.isFlag())
                .build();
    }

    public Page<UnitMeasureDto> toDtoList(Page<UnitMeasureEntity> entities) {
        return entities.map(this::toDto);
    }
}