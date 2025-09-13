package com.rige.mappers;

import com.rige.dto.request.CompanyRequest;
import com.rige.dto.response.CompanyResponse;
import com.rige.entities.CompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICompanyMapper {
    CompanyResponse toResponse(CompanyEntity entity);

    CompanyEntity toEntity(CompanyRequest request);
}
