package com.rige.entities;

import com.rige.enums.MovementType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory_movements")
public class InventoryMovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "from_warehouse_id")
    private WarehouseEntity fromWarehouse;

    @ManyToOne
    @JoinColumn(name = "to_warehouse_id")
    private WarehouseEntity toWarehouse;

    private int quantity;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private MovementType type;
}
