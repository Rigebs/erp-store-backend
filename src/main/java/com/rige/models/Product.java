package com.rige.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Long id;

    private String name;
    private String description;
    private int quantity;
    private double purchasePrice;
    private double salePrice;
    private boolean status;
    private boolean flag;

    private Image image;
    private Brand brand;
    private Category category;
    private UnitMeasure unitMeasure;
    private Line line;
    private Supplier supplier;
}
