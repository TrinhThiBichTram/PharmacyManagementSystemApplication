package com.ttbtram.pharmacyManagementSystem.repository;

import com.ttbtram.pharmacyManagementSystem.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Optional<Receipt> findById(Long receiptID);
    List<Receipt> findBySupplier_SupplierID(Long supplierID);
    List<Receipt> findByUser_UserID(Long userID);
}