package com.abi.studentcart.document.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rollNumber;
    private Long phoneNumber;
    private int quantity;
    @Column(length = 500)
    private String description;
    private String fileName;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] fileData;
    private boolean printed=false;
    private LocalDateTime submittedAt;
    private LocalDateTime printedAt;
}
