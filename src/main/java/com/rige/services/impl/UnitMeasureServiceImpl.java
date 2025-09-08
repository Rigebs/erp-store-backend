package com.rige.services.impl;

import com.rige.dto.request.UnitMeasureRequest;
import com.rige.dto.response.UnitMeasureResponse;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.IUnitMeasureMapper;
import com.rige.repositories.IUnitMeasureRepository;
import com.rige.services.IUnitMeasureService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UnitMeasureServiceImpl implements IUnitMeasureService {

    private final IUnitMeasureRepository unitMeasureRepository;
    private final IUnitMeasureMapper unitMeasureMapper;

    @Override
    public void save(UnitMeasureRequest unitMeasureRequest) {
        var unitMeasure = unitMeasureMapper.toEntity(unitMeasureRequest);
        unitMeasure.setEnabled(true);
        unitMeasure.setFlag(true);
        unitMeasureRepository.save(unitMeasure);
    }

    @Override
    public Page<UnitMeasureResponse> findAll(Pageable pageable) {
        var unitMeasures = unitMeasureRepository.findAll(pageable);
        return unitMeasures.map(unitMeasureMapper::toResponse);
    }

    @Override
    public UnitMeasureResponse findById(Long id) {
        return unitMeasureMapper.toResponse(unitMeasureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit Measure not found with id " + id)));
    }

    @Override
    public void update(Long id, UnitMeasureRequest unitMeasureRequest) {
        var existingUnitMeasure = unitMeasureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit Measure not found with id " + id));

        existingUnitMeasure.setName(unitMeasureRequest.getName());
        existingUnitMeasure.setAbbreviation(unitMeasureRequest.getAbbreviation());
        existingUnitMeasure.setDescription(unitMeasureRequest.getDescription());

        unitMeasureRepository.save(existingUnitMeasure);
    }

    @Override
    public void delete(Long id) {
        var existingUnitMeasure = unitMeasureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit Measure not found with id " + id));
        existingUnitMeasure.setFlag(false);
        unitMeasureRepository.save(existingUnitMeasure);
    }

    @Override
    public void toggleEnabled(Long id) {
        var existingUnitMeasure = unitMeasureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit Measure not found with id " + id));
        existingUnitMeasure.setEnabled(!existingUnitMeasure.isEnabled());
        unitMeasureRepository.save(existingUnitMeasure);
    }
}