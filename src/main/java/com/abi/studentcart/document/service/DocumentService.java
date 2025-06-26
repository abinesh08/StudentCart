package com.abi.studentcart.document.service;

import com.abi.studentcart.document.dto.DocumentResponse;
import com.abi.studentcart.document.dto.DocumentViewData;
import com.abi.studentcart.document.mapper.DocumentMapper;
import com.abi.studentcart.document.model.Document;
import com.abi.studentcart.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;

    public List<DocumentResponse> uploadDocuments(String rollNumber, Long phoneNumber, int quantity
    , String description, List<MultipartFile> files) throws IOException{
        List<DocumentResponse> responseList= new ArrayList<>();

        for(MultipartFile file : files){
            Document document= Document.builder()
                    .rollNumber(rollNumber)
                    .phoneNumber(phoneNumber)
                    .quantity(quantity)
                    .description(description)
                    .fileName(file.getOriginalFilename())
                    .fileData(file.getBytes())
                    .submittedAt(LocalDateTime.now())
                    .printed(false)
                    .build();

            Document saved= documentRepository.save(document);
            responseList.add(DocumentMapper.toResponseDto(saved));
        }
        return responseList;
    }

    public List<DocumentResponse> getDocumentsByPrintedStatus(Boolean printed){
        List<Document> documents;

        if(printed==null){
            documents=documentRepository.findAll();
        }else{
            documents=documentRepository.findByPrinted(printed);
        }
        List<DocumentResponse> responseList=new ArrayList<>();;
        for(Document document : documents){
            responseList.add(DocumentMapper.toResponseDto(document));
        }
        return responseList;
    }

    public DocumentResponse markAsPrinted(Long id){
        Document document= documentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Document not found"));
        document.setPrinted(true);
        document.setPrintedAt(LocalDateTime.now());

        Document updated= documentRepository.save(document);
        return DocumentMapper.toResponseDto(updated);
    }
    public DocumentViewData viewDocument(Long id){
        Document document= documentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Document not found"));

        return DocumentMapper.toViewData(document);
    }
}
