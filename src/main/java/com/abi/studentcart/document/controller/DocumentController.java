package com.abi.studentcart.document.controller;

import com.abi.studentcart.document.dto.DocumentResponse;
import com.abi.studentcart.document.dto.DocumentUploadRequest;
import com.abi.studentcart.document.dto.DocumentViewData;
import com.abi.studentcart.document.service.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<DocumentResponse> uploadDocuments(
            @RequestPart("data")String data,
            @RequestPart("files") List<MultipartFile> files) throws IOException{

        ObjectMapper objectMapper = new ObjectMapper();
        DocumentUploadRequest request = objectMapper.readValue(data, DocumentUploadRequest.class);
        return documentService.uploadDocuments(request.getRollNumber(), request.getPhoneNumber(),
                request.getQuantity(), request.getDescription(),files);
    }

    @GetMapping
    public List<DocumentResponse> getDocumentsByStatus(@RequestParam(name = "printed", required = false)
                                                           Boolean printed){
        return documentService.getDocumentsByPrintedStatus(printed);
    }

    @PutMapping("/mark-printed/{id}")
    public DocumentResponse markAsPrinted(@PathVariable Long id){
        return documentService.markAsPrinted(id);
    }

    @GetMapping("/view/{id}")
    public DocumentViewData viewDocument(@PathVariable Long id){
        return documentService.viewDocument(id);
    }

}
