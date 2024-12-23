package com.rige.services.impl;

import com.rige.dto.LineDto;
import com.rige.mappers.LineMapper;
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
    public List<LineDto> findAllActive() {
        return lineMapper.toDtoList(iLineRepository.findByFlag(true));
    }
}
