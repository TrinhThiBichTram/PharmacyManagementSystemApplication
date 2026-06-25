package com.ttbtram.pharmacyManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private int qty;
    private double discount;
    private String unit;
    private double uniPrice;
    private double total;

    @ManyToOne
    @JsonBackReference("orderCustomer-orderDetails")
    @JoinColumn(name = "orderCustomerID", nullable = false)
    private OrderCustomer orderCustomer;

    @ManyToOne
    @JsonBackReference("warehouse-orderDetails")
    @JoinColumn(name = "warehouseID", nullable = false)
    private Warehouse warehouse;

    @ManyToOne
    @JsonBackReference("product-orderDetails")
    @JoinColumn(name = "productID", nullable = false)
    private Product product;

//    @OneToMany(mappedBy = "orderDetail")
//    @JsonManagedReference("orderDetail-products")
//    private List<Product> products;

}