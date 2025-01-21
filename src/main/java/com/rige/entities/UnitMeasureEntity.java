package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "units_measure")
public class UnitMeasureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String abbreviation;
    private String description;
    private boolean status;
    private boolean flag;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public UnitMeasureEntity(Long unitMeasureId) {
        this.id = unitMeasureId;
    }
}
