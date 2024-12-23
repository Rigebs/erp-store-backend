package com.rige.mappers;

import com.rige.dto.LineDto;
import com.rige.entities.LineEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LineMapper {

    public LineDto toDto(LineEntity entity) {
        if (entity == null) {
            return null;
        }
        return LineDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.isStatus())
                .flag(entity.isFlag())
                .build();
    }

    public List<LineDto> toDtoList(List<LineEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}