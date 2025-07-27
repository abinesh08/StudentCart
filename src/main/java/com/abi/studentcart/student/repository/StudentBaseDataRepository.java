package com.abi.studentcart.student.repository;

import com.abi.studentcart.student.model.StudentBaseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentBaseDataRepository extends JpaRepository<StudentBaseData, String> {
     Optional<StudentBaseData> getByRollNumber(String rollNumber);
}
