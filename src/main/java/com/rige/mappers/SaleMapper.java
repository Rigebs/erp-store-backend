package com.rige.mappers;

import com.rige.dto.request.SaleDetailRequest;
import com.rige.dto.request.SaleRequest;
import com.rige.entities.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleMapper {

    public SaleEntity toEntity(SaleRequest saleRequest) {
        SaleEntity saleEntity = SaleEntity.builder()
                .subtotal(saleRequest.getSubtotal())
                .tax(saleRequest.getTax())
                .discount(saleRequest.getDiscount())
                .total(saleRequest.getTotal())
                .dateTime(LocalDateTime.now())
                .status(saleRequest.isStatus())
                .customer(mapCustomer(saleRequest.getCustomerId()))
                .cashier(mapUser(saleRequest.getCashierId()))
                .saleDetails(mapSaleDetails(saleRequest.getSaleDetails()))
                .build();

        saleEntity.getSaleDetails().forEach(saleDetailEntity -> saleDetailEntity.setSale(saleEntity));

        return saleEntity;
    }

    private CustomerEntity mapCustomer(Long customerId) {
        return customerId != null ? CustomerEntity.builder().id(customerId).build() : null;
    }

    private UserEntity mapUser(Long userId) {
        return userId != null ? UserEntity.builder().id(userId).build() : null;
    }

    private List<SaleDetailEntity> mapSaleDetails(List<SaleDetailRequest> saleDetailRequests) {
        return saleDetailRequests != null ? saleDetailRequests.stream()
                .map(this::mapSaleDetail)
                .collect(Collectors.toList()) : null;
    }

    private SaleDetailEntity mapSaleDetail(SaleDetailRequest saleDetailRequest) {
        ProductEntity productEntity = ProductEntity.builder()
                .id(saleDetailRequest.getProductId())
                .build();

        Double totalPrice = saleDetailRequest.getQuantity() * saleDetailRequest.getPrice();

        return SaleDetailEntity.builder()
                .quantity(saleDetailRequest.getQuantity())
                .unitPrice(saleDetailRequest.getPrice())
                .totalPrice(totalPrice)
                .product(productEntity)
                .build();
    }
}