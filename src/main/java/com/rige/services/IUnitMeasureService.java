package com.rige.services;

import com.rige.dto.UnitMeasureDto;

import java.util.List;

public interface IUnitMeasureService {
    List<UnitMeasureDto> findAllActive();
}
