package com.rige.services;

import com.rige.dto.LineDto;

import java.util.List;

public interface ILineService {
    List<LineDto> findAllActive();
}
