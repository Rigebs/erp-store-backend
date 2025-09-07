package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    @OneToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;
}
