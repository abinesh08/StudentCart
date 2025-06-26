package com.abi.studentcart.document.mapper;

import com.abi.studentcart.document.dto.DocumentResponse;
import com.abi.studentcart.document.dto.DocumentViewData;
import com.abi.studentcart.document.model.Document;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DocumentMapper {

    public static DocumentResponse toResponseDto(Document document){
        DocumentResponse dto= new DocumentResponse();
        dto.setId(document.getId());
        dto.setRollNumber(document.getRollNumber());
        dto.setPhoneNumber(document.getPhoneNumber());
        dto.setQuantity(document.getQuantity());
        dto.setFileName(document.getFileName());
        dto.setPrinted(document.isPrinted());
        dto.setSubmittedAt(document.getSubmittedAt());
        dto.setPrintedAt(document.getPrintedAt());
        return dto;
    }
    public static DocumentViewData toViewData(Document document){
        DocumentViewData dto= new DocumentViewData();
        dto.setFileName(document.getFileName());
        try{
            Path path= Paths.get(document.getFileName());
            String mimeType= Files.probeContentType(path);
            dto.setContentType(mimeType!=null ? mimeType : "application/octet-stream");
        }catch (Exception e){
            dto.setContentType("application/octet-stream");
        }

        dto.setFileData(document.getFileData());
        return dto;
    }


}
