package com.ttbtram.pharmacyManagementSystem.repository;

import com.ttbtram.pharmacyManagementSystem.model.Supplier;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findById(Long supplierID);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    List<Supplier> findByNameContaining(String name);
    Optional<Supplier> findByName(String name);
    List<Supplier> findByPhone(String phone);

}
