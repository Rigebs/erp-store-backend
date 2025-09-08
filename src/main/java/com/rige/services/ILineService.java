package com.rige.services;

import com.rige.dto.request.LineRequest;
import com.rige.dto.response.LineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILineService {
    void save(LineRequest lineRequest);
    Page<LineResponse> findAll(Pageable pageable);
    LineResponse findById(Long id);
    void update(Long id, LineRequest lineRequest);
    void delete(Long id);
    void toggleEnabled(Long id);
}