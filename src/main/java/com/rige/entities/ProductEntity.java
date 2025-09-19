package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@Builder
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
    private String imageUrl;

    @ColumnDefault("1")
    private boolean enabled;

    @ColumnDefault("1")
    private boolean flag;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "unit_measure_id")
    private UnitMeasureEntity unitMeasure;

    @ManyToOne
    @JoinColumn(name = "line_id")
    private LineEntity line;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;
}