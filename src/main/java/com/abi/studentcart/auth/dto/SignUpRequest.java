package com.abi.studentcart.auth.dto;

import com.abi.studentcart.auth.model.Role;
import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String rollNumber;
    private String phoneNumber;
    private String collegeId;
    private String password;
    private Role role;
}
