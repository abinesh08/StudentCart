package com.abi.studentcart.student.service;

import com.abi.studentcart.student.model.StudentBaseData;
import com.abi.studentcart.student.repository.StudentBaseDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentBaseDataRepository studentBaseDataRepository;

    public StudentBaseData saveStudent(StudentBaseData student){
        return studentBaseDataRepository.save(student);
    }
    public StudentBaseData getStudentByRollNumber(String rollNumber){
        return studentBaseDataRepository.findById(rollNumber).orElseThrow(()-> new RuntimeException("Student not found: "
        + rollNumber));
    }
}
