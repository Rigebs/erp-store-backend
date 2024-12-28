package com.rige.mappers;

import com.rige.dto.SupplierDto;
import com.rige.dto.request.SupplierRequest;
import com.rige.entities.SupplierEntity;
import com.rige.models.Supplier;
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

    public Supplier toDomain(SupplierEntity entity) {
        if (entity == null) {
            return null;
        }
        return Supplier.builder()
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

    public SupplierEntity toDbo(SupplierRequest supplierRequest) {
        if (supplierRequest == null) {
            return null;
        }
        return SupplierEntity.builder()
                .name(supplierRequest.getName())
                .contactName(supplierRequest.getContactName())
                .contactEmail(supplierRequest.getContactEmail())
                .phoneNumber(supplierRequest.getPhoneNumber())
                .address(supplierRequest.getAddress())
                .website(supplierRequest.getWebsite())
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