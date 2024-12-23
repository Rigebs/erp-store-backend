package com.rige.services;

import com.rige.dto.BrandDto;

import java.util.List;

public interface IBrandService {
    List<BrandDto> findAllActive();
}
