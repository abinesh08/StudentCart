package com.abi.studentcart.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String rollNumber;
    @Column(unique = true, length=10)
    private String phoneNumber;

    private String password;
    @Enumerated(EnumType.STRING)
    private Role role= Role.STUDENT;

}
