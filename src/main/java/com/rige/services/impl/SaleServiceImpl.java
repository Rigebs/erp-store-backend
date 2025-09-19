package com.rige.services.impl;

import com.rige.dto.request.SaleRequest;
import com.rige.dto.response.SaleResponse;
import com.rige.entities.*;
import com.rige.enums.MovementType;
import com.rige.mappers.ISaleMapper;
import com.rige.repositories.*;
import com.rige.services.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements ISaleService {

    private final ISaleRepository saleRepository;
    private final IProductRepository productRepository;
    private final IStockRepository stockRepository;
    private final IInventoryMovementRepository movementRepository;
    private final IUserRepository userRepository;
    private final ISaleMapper saleMapper;

    @Override
    @Transactional
    public void save(SaleRequest saleRequest) {

        SaleEntity sale = saleMapper.toEntity(saleRequest);
        sale.setDateTime(LocalDateTime.now());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();

        UserEntity userAuthenticated = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<SaleDetailEntity> saleDetails = saleRequest.getSaleDetails().stream().map(detailRequest -> {
            ProductEntity product = productRepository.findById(detailRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            StockEntity stock = stockRepository.findByProductAndWarehouse(product, userAuthenticated.getWarehouse())
                    .orElseThrow(() -> new RuntimeException("Stock not found"));

            if (stock.getQuantity() < detailRequest.getQuantity()) {
                throw new RuntimeException("Not enough stock for product " + product.getName());
            }

            stock.setQuantity(stock.getQuantity() - detailRequest.getQuantity());
            stockRepository.save(stock);

            InventoryMovementEntity movement = InventoryMovementEntity.builder()
                    .product(product)
                    .quantity(detailRequest.getQuantity())
                    .type(MovementType.OUT)
                    .date(LocalDateTime.now())
                    .fromWarehouse(stock.getWarehouse())
                    .build();
            movementRepository.save(movement);

            return SaleDetailEntity.builder()
                    .sale(sale)
                    .product(product)
                    .quantity(detailRequest.getQuantity())
                    .unitPrice(detailRequest.getPrice())
                    .totalPrice(detailRequest.getSubtotal())
                    .build();
        }).toList();

        sale.setSaleDetails(saleDetails);
        saleRepository.save(sale);
    }

    @Override
    public Page<SaleResponse> findAll(Pageable pageable) {
        Page<Long> idsPage = saleRepository.findAllIds(pageable);
        List<SaleEntity> sales = saleRepository.findAllById(idsPage.getContent());
        List<SaleResponse> saleResponses = sales.stream()
                .map(saleMapper::toResponse)
                .toList();

        return new PageImpl<>(
                saleResponses,
                pageable,
                idsPage.getTotalElements()
        );
    }

    @Override
    public SaleResponse findById(Long id) {
        SaleEntity sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with ID: " + id));
        return saleMapper.toResponse(sale);
    }
}
