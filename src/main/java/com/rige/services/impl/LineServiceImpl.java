package com.rige.services.impl;

import com.rige.dto.LineDto;
import com.rige.dto.request.LineRequest;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.LineMapper;
import com.rige.models.Line;
import com.rige.repositories.ILineRepository;
import com.rige.services.ILineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LineServiceImpl implements ILineService {

    private final ILineRepository iLineRepository;
    private final LineMapper lineMapper;

    @Override
    public void save(LineRequest lineRequest) {
        var line = lineMapper.toDbo(lineRequest);
        line.setStatus(true);
        line.setFlag(true);
        iLineRepository.save(line);
    }

    @Override
    public List<LineDto> findAll(Long userId) {
        return lineMapper.toDtoList(iLineRepository.findByFlagAndUserEntity_Id(true, userId));
    }

    @Override
    public List<LineDto> findAllActive(Long userId) {
        return lineMapper.toDtoList(iLineRepository.findByFlagAndStatusAndUserEntity_Id(true, true, userId));
    }

    @Override
    public Line findById(Long id) {
        return lineMapper.toDomain(iLineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found with id " + id)));
    }

    @Override
    public void update(Long id, LineRequest lineRequest) {
        var existingLine = iLineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found with id " + id));

        existingLine.setName(lineRequest.getName());
        existingLine.setDescription(lineRequest.getDescription());

        iLineRepository.save(existingLine);
    }

    @Override
    public void delete(Long id) {
        var existingLine = iLineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found with id " + id));
        existingLine.setFlag(false);
        iLineRepository.save(existingLine);
    }

    @Override
    public void toggleStatus(Long id) {
        var existingLine = iLineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found with id " + id));
        existingLine.setStatus(!existingLine.isStatus());
        iLineRepository.save(existingLine);
    }
}
