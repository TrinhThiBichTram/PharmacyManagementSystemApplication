package com.ttbtram.pharmacyManagementSystem.repository;

import com.ttbtram.pharmacyManagementSystem.model.GroupProduct;
import com.ttbtram.pharmacyManagementSystem.model.GroupSupplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupSupplierRepository extends JpaRepository<GroupSupplier, Long> {
    Optional<GroupSupplier> findById(Long id);
    boolean existsByName(String name);
    Page<GroupSupplier> findByNameContaining(String name, Pageable pageable);

}