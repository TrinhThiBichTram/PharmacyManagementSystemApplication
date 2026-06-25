package com.ttbtram.pharmacyManagementSystem.model;

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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerID;
    @NotEmpty(message = "Tên không thể để trống")
    private String fullName;
    @NotEmpty(message = "Số điện thoại không thể để trống")
    @Pattern(regexp = "^(0[3|5|7|8|9]{1}[0-9]{8}|(\\+84)[3|5|7|8|9]{1}[0-9]{8})$",
            message = "Số điện thoại không hợp lệ")
    private String phone;
    @Email(message = "Địa chỉ email không hợp lệ")
    @NotEmpty(message = "Email không thể để trống")
    private String email;
    private String address;
    private String note;


    @OneToMany(mappedBy = "customer")
    @JsonManagedReference ("customer-orderCustomers")
    private List<OrderCustomer> orderCustomers;
}