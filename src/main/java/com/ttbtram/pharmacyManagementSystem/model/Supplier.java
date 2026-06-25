package com.ttbtram.pharmacyManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;


import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierID;
    @NotEmpty(message = "Tên không thể trống")
    private String name;
    private int taxCode;
    @NotEmpty(message = "Số điện thoại không thể là chuỗi rỗng")
    @Pattern(regexp = "^(0[3|5|7|8|9]{1}[0-9]{8}|(\\+84)[3|5|7|8|9]{1}[0-9]{8})$",
            message = "Số điện thoại không hợp lệ")
    private String phone;
    @Email(message = "Địa chỉ email không hợp lệ")
    @NotEmpty(message = "Email không thể trống")
    private String email;
    private String address;
    private String status;
    private String note;

    @ManyToOne
    @JsonBackReference("groupSupplier-suppliers")
    @JoinColumn(name = "groupSupplierID", nullable = false)
    private GroupSupplier groupSupplier;

    @OneToMany(mappedBy = "supplier")
    @JsonManagedReference("supplier-products")
    private List<Product> products;

    @OneToMany(mappedBy = "supplier")
    @JsonManagedReference("supplier-receipts")
    private List<Receipt> receipts;
}

