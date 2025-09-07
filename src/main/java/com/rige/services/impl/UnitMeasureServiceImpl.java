package com.rige.services.impl;

import com.rige.dto.UnitMeasureDto;
import com.rige.dto.request.UnitMeasureRequest;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.UnitMeasureMapper;
import com.rige.repositories.IUnitMeasureRepository;
import com.rige.services.IUnitMeasureService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UnitMeasureServiceImpl implements IUnitMeasureService {

    private final IUnitMeasureRepository iUnitMeasureRepository;
    private final UnitMeasureMapper unitMeasureMapper;

    @Override
    public void save(UnitMeasureRequest unitMeasureRequest) {
        var unitMeasure = unitMeasureMapper.toDbo(unitMeasureRequest);
        unitMeasure.setEnabled(true);
        unitMeasure.setFlag(true);
        iUnitMeasureRepository.save(unitMeasure);
    }

    @Override
    public Page<UnitMeasureDto> findAll(Long userId, Pageable pageable) {
        return unitMeasureMapper.toDtoList(iUnitMeasureRepository.findAll(pageable));
    }

    @Override
    public Page<UnitMeasureDto> findAllActive(Long userId, Pageable pageable) {
        return unitMeasureMapper.toDtoList(iUnitMeasureRepository.findAll(pageable));
    }

    @Override
    public UnitMeasureDto findById(Long id) {
        return unitMeasureMapper.toDto(iUnitMeasureRepository.findById(id)
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
        existingUnitMeasure.setEnabled(!existingUnitMeasure.isEnabled());
        iUnitMeasureRepository.save(existingUnitMeasure);
    }
}
