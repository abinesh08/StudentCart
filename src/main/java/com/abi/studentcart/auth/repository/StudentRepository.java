package com.abi.studentcart.auth.repository;

import com.abi.studentcart.auth.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByRollNumber(String rollNumber);
}
