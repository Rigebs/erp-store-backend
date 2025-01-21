package com.rige.mappers;

import com.rige.dto.LineDto;
import com.rige.dto.request.LineRequest;
import com.rige.entities.LineEntity;
import com.rige.entities.UserEntity;
import com.rige.models.Line;
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

    public Line toDomain(LineEntity entity) {
        if (entity == null) {
            return null;
        }
        return Line.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.isStatus())
                .flag(entity.isFlag())
                .build();
    }

    public LineEntity toDbo(LineRequest lineRequest) {
        if (lineRequest == null) {
            return null;
        }
        return LineEntity.builder()
                .name(lineRequest.getName())
                .description(lineRequest.getDescription())
                .userEntity(UserEntity.builder().id(lineRequest.getUserId()).build())
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