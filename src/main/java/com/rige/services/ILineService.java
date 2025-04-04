package com.rige.services;

import com.rige.dto.LineDto;
import com.rige.dto.request.LineRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILineService {
    void save(LineRequest lineRequest);
    Page<LineDto> findAll(Long userId, Pageable pageable);
    Page<LineDto> findAllActive(Long userId, Pageable pageable);
    LineDto findById(Long id);
    void update(Long id, LineRequest lineRequest);
    void delete(Long id);
    void toggleStatus(Long id);
}