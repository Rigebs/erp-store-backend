package com.rige.services.impl;

import com.rige.dto.request.CustomerRequest;
import com.rige.dto.response.CustomerResponse;
import com.rige.entities.CompanyEntity;
import com.rige.entities.CustomerEntity;
import com.rige.entities.PersonEntity;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.filters.CustomerFilter;
import com.rige.mappers.ICompanyMapper;
import com.rige.mappers.ICustomerMapper;
import com.rige.mappers.IPersonMapper;
import com.rige.repositories.ICompanyRepository;
import com.rige.repositories.ICustomerRepository;
import com.rige.repositories.IPersonRepository;
import com.rige.services.ICustomerService;
import com.rige.specifications.CustomerSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;
    private final ICompanyMapper companyMapper;
    private final IPersonMapper personMapper;
    private final IPersonRepository personRepository;
    private final ICompanyRepository companyRepository;

    @Override
    public CustomerResponse save(CustomerRequest request) {
        CustomerEntity entity = customerMapper.toEntity(request);

        if (entity.getPerson() == null && entity.getCompany() == null) {
            throw new IllegalArgumentException("Customer must have either a Person or a Company");
        }

        entity.setEnabled(true);
        var saved = customerRepository.save(entity);
        return customerMapper.toResponse(saved);
    }

    @Override
    public Page<CustomerResponse> findAll(CustomerFilter filter, Pageable pageable) {
        return customerRepository.findAll(CustomerSpecification.build(filter), pageable)
                .map(customerMapper::toResponse);
    }

    @Override
    public CustomerResponse findById(Long id) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        return customerMapper.toResponse(entity);
    }

    @Override
    public void update(Long id, CustomerRequest request) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        customerMapper.updateFromRequest(request, entity);

        if (request.getPerson() != null) {
            PersonEntity person = personMapper.toEntity(request.getPerson());
            personRepository.save(person);
            entity.setPerson(person);
        }

        if (request.getCompany() != null) {
            CompanyEntity company = companyMapper.toEntity(request.getCompany());
            companyRepository.save(company);
            entity.setCompany(company);
        }

        customerRepository.save(entity);
    }

    @Override
    public void toggleEnabled(Long id) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        entity.setEnabled(!entity.isEnabled());
        customerRepository.save(entity);
    }
}
