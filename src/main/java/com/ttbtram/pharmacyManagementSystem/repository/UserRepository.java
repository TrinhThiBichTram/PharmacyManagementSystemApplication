package com.ttbtram.pharmacyManagementSystem.repository;

import com.ttbtram.pharmacyManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userID);
    boolean existsByUsername( String username);
    List<User> findByFullNameContaining(String fullName);
}
