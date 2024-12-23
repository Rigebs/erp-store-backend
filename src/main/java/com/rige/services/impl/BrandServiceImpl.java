package com.rige.services.impl;

import com.rige.dto.BrandDto;
import com.rige.mappers.BrandMapper;
import com.rige.repositories.IBrandRepository;
import com.rige.services.IBrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements IBrandService {

    private final IBrandRepository iBrandRepository;
    private final BrandMapper brandMapper;

    @Override
    public List<BrandDto> findAllActive() {
        return brandMapper.toDtoList(iBrandRepository.findByFlag(true));
    }
}
