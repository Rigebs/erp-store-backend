package com.rige.mappers;

import com.rige.dto.SupplierDto;
import com.rige.entities.SupplierEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierMapper {

    public SupplierDto toDto(SupplierEntity entity) {
        if (entity == null) {
            return null;
        }
        return SupplierDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .contactName(entity.getContactName())
                .contactEmail(entity.getContactEmail())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .website(entity.getWebsite())
                .status(entity.isStatus())
                .flag(entity.isFlag())
                .build();
    }

    public List<SupplierDto> toDtoList(List<SupplierEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}