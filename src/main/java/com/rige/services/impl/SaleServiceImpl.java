package com.rige.services.impl;

import com.rige.dto.request.SaleRequest;
import com.rige.dto.response.SaleResponse;
import com.rige.entities.SaleEntity;
import com.rige.mappers.ISaleMapper;
import com.rige.repositories.ISaleRepository;
import com.rige.services.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements ISaleService {

    private final ISaleRepository saleRepository;
    private final ISaleMapper saleMapper;

    @Override
    public void save(SaleRequest saleRequest) {
        saleRepository.save(saleMapper.toEntity(saleRequest));
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
