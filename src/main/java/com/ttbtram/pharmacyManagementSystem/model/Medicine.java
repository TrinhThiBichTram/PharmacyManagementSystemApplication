package com.ttbtram.pharmacyManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Medicine extends Product {
    @NotEmpty(message = "Số đăng ký không thể để trống")
    private String registrationNumber;
    private String mainActiveIngredient;
    private String medicationDosage;
    private String preserve;
}