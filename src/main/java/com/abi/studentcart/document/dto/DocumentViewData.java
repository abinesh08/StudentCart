package com.abi.studentcart.document.dto;

import lombok.Data;

@Data
public class DocumentViewData {
    private String fileName;
    private String contentType;
    private byte[] fileData;
}
