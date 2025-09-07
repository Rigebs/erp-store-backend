package com.rige.mappers;

import com.rige.dto.LineDto;
import com.rige.dto.request.LineRequest;
import com.rige.entities.LineEntity;
import com.rige.entities.UserEntity;
import com.rige.models.Line;
import org.springframework.data.domain.Page;
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
                .status(entity.isEnabled())
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
                .status(entity.isEnabled())
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
                .build();
    }

    public Page<LineDto> toDtoList(Page<LineEntity> entities) {
        return entities.map(this::toDto);
    }
}