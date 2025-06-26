package com.abi.studentcart.document.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponse {
    private Long id;
    private String rollNumber;
    private Long phoneNumber;
    private int quantity;
    private String fileName;
    private boolean printed;
    private LocalDateTime submittedAt;
    private LocalDateTime printedAt;
}
