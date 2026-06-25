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
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "time", insertable = false, updatable = false)
    private LocalDate time;
    @NotEmpty(message = "Hoạt động không thể để trống")
    private String activity;
    private String note;

    @ManyToOne
    @JsonBackReference ("user-histories")
    @JoinColumn(name = "userID", nullable = false)
    private User user;
}
