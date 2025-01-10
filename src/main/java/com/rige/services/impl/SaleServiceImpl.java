package com.rige.services.impl;

import com.rige.dto.SaleDto;
import com.rige.dto.request.SaleRequest;
import com.rige.mappers.SaleMapper;
import com.rige.repositories.ISaleRepository;
import com.rige.services.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements ISaleService {

    private final ISaleRepository iSaleRepository;
    private final SaleMapper saleMapper;

    @Override
    public void save(SaleRequest saleRequest) {
        iSaleRepository.save(saleMapper.toEntity(saleRequest));
    }

    @Override
    public List<SaleDto> findAll() {
        return List.of();
    }

    @Override
    public SaleDto findById(Long id) {
        return null;
    }
}
