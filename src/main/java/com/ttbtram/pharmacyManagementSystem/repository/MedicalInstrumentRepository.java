package com.ttbtram.pharmacyManagementSystem.repository;

import com.ttbtram.pharmacyManagementSystem.model.MedicalInstrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalInstrumentRepository extends JpaRepository<MedicalInstrument, Long> {
    boolean existsByNameAndManufacturer(String name, String manufacturer);
}
