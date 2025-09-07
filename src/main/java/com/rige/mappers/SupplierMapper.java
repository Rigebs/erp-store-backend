package com.rige.mappers;

import com.rige.dto.SupplierDto;
import com.rige.dto.request.SupplierRequest;
import com.rige.entities.SupplierEntity;
import com.rige.entities.UserEntity;
import com.rige.models.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

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
                .status(entity.isEnabled())
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
                .status(entity.isEnabled())
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

    public Page<SupplierDto> toDtoList(Page<SupplierEntity> entities) {
        return entities.map(this::toDto);
    }
}