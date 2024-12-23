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
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double purchasePrice;
    private double salePrice;
    private boolean status;
    private boolean flag;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brandEntity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "unit_measure_id")
    private UnitMeasureEntity unitMeasureEntity;

    @ManyToOne
    @JoinColumn(name = "line_id")
    private LineEntity lineEntity;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierEntity;
}