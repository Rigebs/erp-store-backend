package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("1")
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @OneToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
}
