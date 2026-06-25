package com.ttbtram.pharmacyManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptID;
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
    private String deliverer;
    private double discount;
    private double total;

    @ManyToOne
    @JsonBackReference("supplier-receipts")
    @JoinColumn(name = "supplierID", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "receipt")
    @JsonManagedReference("receipt-receiptDetails")
    private List<ReceiptDetail> receiptDetails;

    @ManyToOne
    @JsonBackReference("user-receipts")
    @JoinColumn(name = "userID", nullable = false)
    private User user;
}
