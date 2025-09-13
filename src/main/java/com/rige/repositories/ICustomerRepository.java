package com.rige.repositories;

import com.rige.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Override
    @NonNull
    @EntityGraph(attributePaths = {
            "person",
            "company"
    })
    Page<CustomerEntity> findAll(@NonNull Pageable pageable);
}
