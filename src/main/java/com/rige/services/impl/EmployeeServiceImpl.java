package com.rige.services.impl;

import com.rige.dto.request.EmployeeRequest;
import com.rige.dto.response.EmployeeResponse;
import com.rige.entities.EmployeeEntity;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.IEmployeeMapper;
import com.rige.repositories.IEmployeeRepository;
import com.rige.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final IEmployeeMapper employeeMapper;

    @Override
    public void save(EmployeeRequest request) {
        var entity = employeeMapper.toEntity(request);
        employeeRepository.save(entity);
    }

    @Override
    public Page<EmployeeResponse> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toResponse);
    }

    @Override
    public EmployeeResponse findById(Long id) {
        var entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        return employeeMapper.toResponse(entity);
    }

    @Override
    public void update(Long id, EmployeeRequest request) {
        EmployeeEntity entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        employeeMapper.updateFromRequest(request, entity);
        employeeRepository.save(entity);
    }
}