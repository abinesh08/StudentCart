package com.abi.studentcart.student.controller;

import com.abi.studentcart.student.model.StudentBaseData;
import com.abi.studentcart.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public StudentBaseData registerStudent(@RequestBody StudentBaseData student){
        return studentService.saveStudent(student);
    }

    @GetMapping("/{rollNumber}")
    public StudentBaseData getStudent(@PathVariable String rollNumber){
        return studentService.getStudentByRollNumber(rollNumber);
    }


}
