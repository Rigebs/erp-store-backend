package com.rige.services.impl;

import com.rige.dto.request.LineRequest;
import com.rige.dto.response.LineResponse;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.ILineMapper;
import com.rige.repositories.ILineRepository;
import com.rige.services.ILineService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LineServiceImpl implements ILineService {

    private final ILineRepository lineRepository;
    private final ILineMapper lineMapper;

    @Override
    public void save(LineRequest lineRequest) {
        var line = lineMapper.toEntity(lineRequest);
        line.setEnabled(true);
        line.setFlag(true);
        lineRepository.save(line);
    }

    @Override
    public Page<LineResponse> findAll(Pageable pageable) {
        var lines = lineRepository.findAll(pageable);
        return lines.map(lineMapper::toResponse);
    }

    @Override
    public LineResponse findById(Long id) {
        return lineMapper.toResponse(lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found with id " + id)));
    }

    @Override
    public void update(Long id, LineRequest lineRequest) {
        var existingLine = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found with id " + id));

        existingLine.setName(lineRequest.getName());
        existingLine.setDescription(lineRequest.getDescription());

        lineRepository.save(existingLine);
    }

    @Override
    public void delete(Long id) {
        var existingLine = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found with id " + id));
        existingLine.setFlag(false);
        lineRepository.save(existingLine);
    }

    @Override
    public void toggleEnabled(Long id) {
        var existingLine = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found with id " + id));
        existingLine.setEnabled(!existingLine.isEnabled());
        lineRepository.save(existingLine);
    }
}