package com.abi.studentcart.auth.repository;

import com.abi.studentcart.auth.model.ShopAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShopAdminRepository extends JpaRepository<ShopAdmin, Long> {
    Optional<ShopAdmin> findByCollegeId(String collegeId);
}
