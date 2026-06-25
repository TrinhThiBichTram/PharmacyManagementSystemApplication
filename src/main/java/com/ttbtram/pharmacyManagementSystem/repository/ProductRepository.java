package com.ttbtram.pharmacyManagementSystem.repository;

import com.ttbtram.pharmacyManagementSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long productID);
    List<Product> findByNameContaining(String name);
}
