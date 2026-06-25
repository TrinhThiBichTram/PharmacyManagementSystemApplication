package com.ttbtram.pharmacyManagementSystem.repository;

import com.ttbtram.pharmacyManagementSystem.model.GroupProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupProductRepository extends JpaRepository<GroupProduct, Long> {
    Optional<GroupProduct> findById(Long id);
    boolean existsByName(String name);
    Page<GroupProduct> findByNameContaining(String name, Pageable pageable);

}

