package com.ttbtram.pharmacyManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Tên không thể để trống")
    private String name;

    @Column(nullable = false)
    private String status;

    private String note;

    @OneToMany(mappedBy = "groupSupplier")
    @JsonManagedReference ("groupSupplier-suppliers")
    private List<Supplier> suppliers;

}
