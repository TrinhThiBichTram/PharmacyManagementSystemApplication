package com.ttbtram.pharmacyManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;
    @Column(name = "date", updatable = false)
    private LocalDateTime date;
    @PrePersist
    public void onPrePersist() {
        if (date == null) {
            date = LocalDateTime.now();
        }
    }
    @NotEmpty(message = "Loại không thể trống")
    private String type;
    private double discount;
    private double VAT;
    private double total;
    private String prescriptionID;
    private String medicalExaminationPlaceP;
    private String patientIDP;
    private String patientNameP;
    private String patientAdressP;
    private String diseaseNameP;
    private String doctorP;
    private String dateP;

    @ManyToOne
    @JsonBackReference("customer-orderCustomers")
    @JoinColumn(name = "customerID", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orderCustomer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("orderCustomer-orderDetails")
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JsonBackReference("user-orderCustomers")
    @JoinColumn(name = "userID", nullable = false)
    private User user;
}