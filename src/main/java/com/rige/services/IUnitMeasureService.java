package com.rige.services;

import com.rige.dto.request.UnitMeasureRequest;
import com.rige.dto.response.UnitMeasureResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUnitMeasureService {
    void save(UnitMeasureRequest unitMeasureRequest);
    Page<UnitMeasureResponse> findAll(Pageable pageable);
    UnitMeasureResponse findById(Long id);
    void update(Long id, UnitMeasureRequest unitMeasureRequest);
    void delete(Long id);
    void toggleEnabled(Long id);
}