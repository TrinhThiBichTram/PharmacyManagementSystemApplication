package com.ttbtram.pharmacyManagementSystem.repository;


import com.ttbtram.pharmacyManagementSystem.model.OrderCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface OrderCustomerRepository extends JpaRepository<OrderCustomer, Long> {
    Optional<OrderCustomer> findById(Long orderID);
    List<OrderCustomer> findByCustomer_CustomerID(Long customerID);
    List<OrderCustomer> findByUser_UserID(Long userID);
}