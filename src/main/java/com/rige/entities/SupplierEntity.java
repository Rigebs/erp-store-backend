package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "suppliers")
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactName;
    private String contactEmail;
    private String phoneNumber;
    private String address;
    private String website;
    private boolean status;
    private boolean flag;

    public SupplierEntity(Long supplierId) {
        this.id = supplierId;
    }
}
