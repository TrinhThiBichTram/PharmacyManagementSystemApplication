package com.ttbtram.pharmacyManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReceiptDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private int qty;
    private String unit;
    private String productionBatch;
    private LocalDate expirationDate;
    private double priceBeforeTax;
    private double discount;
    private double priceAfterTax;
    private double VAT;
    private double total;

    @ManyToOne
    @JsonBackReference("receipt-receiptDetails")
    @JoinColumn(name = "receiptID", nullable = false)
    private Receipt receipt;

    @ManyToOne
    @JsonBackReference("warehouse-receiptDetails")
    @JoinColumn(name = "warehouseID", nullable = false)
    private Warehouse warehouse;

//    @OneToMany(mappedBy = "receiptDetail")
//    @JsonManagedReference("receiptDetail-products")
//    private List<Product> products;
    @ManyToOne
    @JsonBackReference("product-receiptDetails")
    @JoinColumn(name = "productID", nullable = false)
    private Product product;



}

