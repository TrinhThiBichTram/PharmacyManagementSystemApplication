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
public class MedicalInstrument extends Product{
    private String specification;
    @NotEmpty(message = "Số lưu hành không thể để trống ")
    private String circulationNumber;
}
