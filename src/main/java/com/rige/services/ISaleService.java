package com.rige.services;

import com.rige.dto.SaleDto;
import com.rige.dto.custom.FullSaleDetailsDto;
import com.rige.dto.request.SaleRequest;

import java.util.List;

public interface ISaleService {
    void save(SaleRequest saleRequest);
    List<SaleDto> findAllByUser(Long userId);
    FullSaleDetailsDto findById(Long id);
}
