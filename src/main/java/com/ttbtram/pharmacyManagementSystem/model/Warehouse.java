package com.ttbtram.pharmacyManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseID;
    private String name;
    private int minimumQty;
    private int maximumQty;
    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "warehouse")
    @JsonManagedReference("warehouse-receiptDetails")
    private List<ReceiptDetail> receiptDetails;

    @OneToMany(mappedBy = "warehouse")
    @JsonManagedReference("warehouse-inventories")
    private List<Inventory> inventories;

    @OneToMany(mappedBy = "warehouse")
    @JsonManagedReference("warehouse-orderDetails")
    private List<OrderDetail> orderDetails;


}

