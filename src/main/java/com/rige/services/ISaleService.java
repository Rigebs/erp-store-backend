package com.rige.services;

import com.rige.dto.SaleDto;
import com.rige.dto.request.SaleRequest;

import java.util.List;

public interface ISaleService {
    void save(SaleRequest saleRequest);
    List<SaleDto> findAll();
    SaleDto findById(Long id);
}
