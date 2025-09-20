package com.rige.services;

import com.rige.dto.request.SaleRequest;
import com.rige.dto.response.SaleResponse;
import com.rige.filters.SaleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISaleService {
    void save(SaleRequest saleRequest);
    Page<SaleResponse> findAll(SaleFilter filter, Pageable pageable);
    SaleResponse findById(Long id);
}
