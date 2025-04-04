package com.rige.services.impl;

import com.rige.dto.LineDto;
import com.rige.dto.request.LineRequest;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.LineMapper;
import com.rige.repositories.ILineRepository;
import com.rige.services.ILineService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<LineDto> findAll(Long userId, Pageable pageable) {
        return lineMapper.toDtoList(iLineRepository.findByFlagAndUserEntity_Id(true, userId, pageable));
    }

    @Override
    public Page<LineDto> findAllActive(Long userId, Pageable pageable) {
        return lineMapper.toDtoList(iLineRepository.findByFlagAndStatusAndUserEntity_Id(true, true, userId, pageable));
    }

    @Override
    public LineDto findById(Long id) {
        return lineMapper.toDto(iLineRepository.findById(id)
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
