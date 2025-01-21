package com.rige.services.impl;

import com.rige.dto.UnitMeasureDto;
import com.rige.dto.request.UnitMeasureRequest;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.UnitMeasureMapper;
import com.rige.models.UnitMeasure;
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
    public void save(UnitMeasureRequest unitMeasureRequest) {
        var unitMeasure = unitMeasureMapper.toDbo(unitMeasureRequest);
        unitMeasure.setStatus(true);
        unitMeasure.setFlag(true);
        iUnitMeasureRepository.save(unitMeasure);
    }

    @Override
    public List<UnitMeasureDto> findAll(Long userId) {
        return unitMeasureMapper.toDtoList(iUnitMeasureRepository.findByFlagAndUserEntity_Id(true, userId));
    }

    @Override
    public List<UnitMeasureDto> findAllActive(Long userId) {
        return unitMeasureMapper.toDtoList(iUnitMeasureRepository.findByFlagAndStatusAndUserEntity_Id(true, true, userId));
    }

    @Override
    public UnitMeasure findById(Long id) {
        return unitMeasureMapper.toDomain(iUnitMeasureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit Measure not found with id " + id)));
    }

    @Override
    public void update(Long id, UnitMeasureRequest unitMeasureRequest) {
        var existingUnitMeasure = iUnitMeasureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit Measure not found with id " + id));

        existingUnitMeasure.setName(unitMeasureRequest.getName());
        existingUnitMeasure.setAbbreviation(unitMeasureRequest.getAbbreviation());
        existingUnitMeasure.setDescription(unitMeasureRequest.getDescription());

        iUnitMeasureRepository.save(existingUnitMeasure);
    }

    @Override
    public void delete(Long id) {
        var existingUnitMeasure = iUnitMeasureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit Measure not found with id " + id));
        existingUnitMeasure.setFlag(false);
        iUnitMeasureRepository.save(existingUnitMeasure);
    }

    @Override
    public void toggleStatus(Long id) {
        var existingUnitMeasure = iUnitMeasureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit Measure not found with id " + id));
        existingUnitMeasure.setStatus(!existingUnitMeasure.isStatus());
        iUnitMeasureRepository.save(existingUnitMeasure);
    }
}
