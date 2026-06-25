package com.ttbtram.pharmacyManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String fullName;
    private String phone;
    @Column(nullable=false, unique=true)
    @Email(message = "Email không hợp lệ")
    @NotEmpty(message = "Email không thể trống")
    private String email;
    @NotEmpty(message = "Username không thể trống")
    private String username;
    @NotEmpty(message = "Password không thể trống")
    private String password;
    private String account;

    @Column(name = "creationTime", updatable = false)
    private LocalDateTime creationTime;
    @PrePersist
    public void onPrePersist() {
        if (creationTime == null) {
            creationTime = LocalDateTime.now();
        }
    }

    @Column(name = "lastLoginTime")
    private LocalDateTime lastLoginTime;
    public void updateLastLoginTime() {
        this.lastLoginTime = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-histories")
    private List<History> histories;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-orderCustomers")
    private List<OrderCustomer> orderCustomers;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-receipts")
    private List<Receipt> receipts;
}
