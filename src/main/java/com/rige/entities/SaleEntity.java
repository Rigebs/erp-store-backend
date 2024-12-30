package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "sales")
@NoArgsConstructor
@AllArgsConstructor
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private Double subtotal;
    private Double total;
    private Double tax;
    private Double discount;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "cashier_id")
    private UserEntity cashier;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleDetailEntity> saleDetails;
}