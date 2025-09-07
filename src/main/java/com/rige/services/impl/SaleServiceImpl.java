package com.rige.services.impl;

import com.rige.dto.SaleDto;
import com.rige.dto.custom.FullSaleDetailsDto;
import com.rige.dto.request.SaleRequest;
import com.rige.entities.SaleEntity;
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
    public List<SaleDto> findAllByUser(Long userId) {
        return saleMapper.toDtoList(iSaleRepository.findByCashier_Id(userId));
    }

    @Override
    public FullSaleDetailsDto findById(Long id) {
        SaleEntity sale = iSaleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with ID: " + id));
        return saleMapper.toFullDto(sale);
    }
}
