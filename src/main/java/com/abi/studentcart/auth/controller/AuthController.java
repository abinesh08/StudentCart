package com.abi.studentcart.auth.controller;

import com.abi.studentcart.auth.dto.LoginRequest;
import com.abi.studentcart.auth.dto.SignUpRequest;
import com.abi.studentcart.auth.dto.TokenResponse;
import com.abi.studentcart.auth.model.Role;
import com.abi.studentcart.auth.model.ShopAdmin;
import com.abi.studentcart.auth.model.Student;
import com.abi.studentcart.auth.repository.ShopAdminRepository;
import com.abi.studentcart.auth.repository.StudentRepository;
import com.abi.studentcart.auth.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final StudentRepository studentRepository;
    private final ShopAdminRepository shopAdminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequest request){
        if(request.getRole()== Role.STUDENT){
            if(studentRepository.findByRollNumber(request.getRollNumber()).isPresent()){
                throw new RuntimeException("Roll number already registered and You can log in");
            }

            Student student= new Student();
            student.setName(request.getName());
            student.setRollNumber(request.getRollNumber());
            student.setPhoneNumber(request.getPhoneNumber());
            student.setPassword(passwordEncoder.encode(request.getPassword()));
            student.setRole(Role.STUDENT);

            studentRepository.save(student);
            return "Student signed up successfully";
        }else if(request.getRole()==Role.ADMIN){
            if(shopAdminRepository.findByCollegeId(request.getCollegeId()).isPresent()){
                throw new RuntimeException("College id already registered");
            }

            ShopAdmin admin=new ShopAdmin();
            admin.setCollegeId(request.getCollegeId());
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
            admin.setRole(Role.ADMIN);

            shopAdminRepository.save(admin);
            return "Admin signed up successfully";
        }
        throw new RuntimeException("Invalid role");
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request){
        String username= request.getUsername();
        String password= request.getPassword();

        Student student= studentRepository.findByRollNumber(username).orElse(null);
        if(student!=null && passwordEncoder.matches(password, student.getPassword())){
            String token= jwtUtil.generateToken(student.getRollNumber());
            return new TokenResponse(token);
        }

        ShopAdmin admin=shopAdminRepository.findByCollegeId(username).orElse(null);
        if(admin!=null && passwordEncoder.matches(password, admin.getPassword())){
            String token = jwtUtil.generateToken(admin.getCollegeId());
            return new TokenResponse(token);
        }
        throw new RuntimeException("Invalid credentials");
    }


}
