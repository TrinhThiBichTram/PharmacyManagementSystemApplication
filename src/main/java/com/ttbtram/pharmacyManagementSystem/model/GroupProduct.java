package com.ttbtram.pharmacyManagementSystem.model;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Tên không thể để trống")
    private String name;
    private String status;
    private String note;

    @OneToMany(mappedBy = "groupProduct")
    @JsonManagedReference ("groupProduct-products")
    private List<Product> products;
}