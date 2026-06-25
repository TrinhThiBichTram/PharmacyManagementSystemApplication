package com.ttbtram.pharmacyManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    @NotEmpty(message = "Tên không thể trống")
    private String name;
    private String unit;
    private double discount;
    private String packet;
    private String manufacturer;
    private String manufacturingCountry;
    private String image;
    private String status;
    private double price;

    @ManyToOne
    @JsonBackReference("groupProduct-products")
    @JoinColumn(name = "groupProductID", nullable = false)
    private GroupProduct groupProduct;


    @OneToMany(mappedBy = "product")
    @JsonManagedReference("product-orderDetails")
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JsonBackReference("supplier-products")
    @JoinColumn(name = "supplierID", nullable = true)
    private Supplier supplier;


//    @ManyToOne
//    @JsonBackReference("receiptDetail-products")
//    @JoinColumn(name = "receiptDetailID", nullable = true)
//    private ReceiptDetail receiptDetail;
    @OneToMany(mappedBy = "product")
    @JsonManagedReference("product-receiptDetails")
    private List<ReceiptDetail> receiptDetails;


    @OneToMany(mappedBy = "product")
    @JsonManagedReference("product-inventories")
    private List<Inventory> inventories;

}
