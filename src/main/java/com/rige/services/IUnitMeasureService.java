package com.rige.services;

import com.rige.dto.UnitMeasureDto;
import com.rige.dto.request.UnitMeasureRequest;
import com.rige.models.UnitMeasure;

import java.util.List;

public interface IUnitMeasureService {
    void save(UnitMeasureRequest unitMeasureRequest);
    List<UnitMeasureDto> findAll();
    List<UnitMeasureDto> findAllActive();
    UnitMeasure findById(Long id);
    void update(Long id, UnitMeasureRequest unitMeasureRequest);
    void delete(Long id);
    void toggleStatus(Long id);
}