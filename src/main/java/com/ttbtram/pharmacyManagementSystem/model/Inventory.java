package com.ttbtram.pharmacyManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryID;
    private String name;
    private int currentQty;
    private LocalDate expirationDate;

    @ManyToOne
    @JsonBackReference("warehouse-inventories")
    @JoinColumn(name = "warehouseID", nullable = false)
    private Warehouse warehouse;

    @ManyToOne
    @JsonBackReference("product-inventories")
    @JoinColumn(name = "productID", nullable = false)
    private Product product;

}