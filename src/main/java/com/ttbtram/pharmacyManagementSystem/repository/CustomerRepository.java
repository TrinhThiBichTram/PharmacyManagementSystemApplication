package com.ttbtram.pharmacyManagementSystem.repository;

import com.ttbtram.pharmacyManagementSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(Long customerID);
    boolean existsByFullNameAndPhone(String fullName, String phone);
    List<Customer> findByFullNameContaining(String fullName);
    Optional<Customer> findByFullName(String fullName);
    List<Customer> findByPhone(String phone);
}
