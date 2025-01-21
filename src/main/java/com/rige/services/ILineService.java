package com.rige.services;

import com.rige.dto.LineDto;
import com.rige.dto.request.LineRequest;
import com.rige.models.Line;

import java.util.List;

public interface ILineService {
    void save(LineRequest lineRequest);
    List<LineDto> findAll(Long userId);
    List<LineDto> findAllActive(Long userId);
    Line findById(Long id);
    void update(Long id, LineRequest lineRequest);
    void delete(Long id);
    void toggleStatus(Long id);
}