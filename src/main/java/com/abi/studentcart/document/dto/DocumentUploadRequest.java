package com.abi.studentcart.document.dto;

import lombok.Data;

@Data
public class DocumentUploadRequest {
    private String rollNumber;
    private Long phoneNumber;
    private int quantity;
    private String description;
}
