package com.rige.repositories;

import com.rige.entities.EmployeeEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Override
    @NonNull
    @EntityGraph(attributePaths = {
            "person"
    })
    Page<EmployeeEntity> findAll(@NonNull Pageable pageable);
}