package com.rige.services.impl;

import com.rige.dto.UnitMeasureDto;
import com.rige.mappers.UnitMeasureMapper;
import com.rige.repositories.IUnitMeasureRepository;
import com.rige.services.IUnitMeasureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UnitMeasureServiceImpl implements IUnitMeasureService {

    private final IUnitMeasureRepository iUnitMeasureRepository;
    private final UnitMeasureMapper unitMeasureMapper;

    @Override
    public List<UnitMeasureDto> findAllActive() {
        return unitMeasureMapper.toDtoList(iUnitMeasureRepository.findByFlag(true));
    }
}
