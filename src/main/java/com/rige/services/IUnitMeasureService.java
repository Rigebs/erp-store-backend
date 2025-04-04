package com.rige.services;

import com.rige.dto.UnitMeasureDto;
import com.rige.dto.request.UnitMeasureRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUnitMeasureService {
    void save(UnitMeasureRequest unitMeasureRequest);
    Page<UnitMeasureDto> findAll(Long userId, Pageable pageable);
    Page<UnitMeasureDto> findAllActive(Long userId, Pageable pageable);
    UnitMeasureDto findById(Long id);
    void update(Long id, UnitMeasureRequest unitMeasureRequest);
    void delete(Long id);
    void toggleStatus(Long id);
}