package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "sale_details")
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private SaleEntity sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}