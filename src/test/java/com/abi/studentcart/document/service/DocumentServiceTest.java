package com.abi.studentcart.document.service;

import com.abi.studentcart.document.dto.DocumentResponse;
import com.abi.studentcart.document.dto.DocumentViewData;
import com.abi.studentcart.document.model.Document;
import com.abi.studentcart.document.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DocumentServiceTest {
    @Mock
    private DocumentRepository documentRepository;
    @InjectMocks
    private DocumentService documentService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadDocuments() throws IOException {
        MultipartFile mockFile= mock(MultipartFile.class);
        when(mockFile.getOriginalFilename()).thenReturn("test.pdf");
        when(mockFile.getBytes()).thenReturn("dummy data".getBytes());

        Document mockSaved = Document.builder()
                .id(1L)
                .rollNumber("R123")
                .phoneNumber(1234567890L)
                .quantity(2)
                .description("Test Desc")
                .fileName("test.pdf")
                .fileData("dummy data".getBytes())
                .submittedAt(LocalDateTime.now())
                .printed(false)
                .build();

        when(documentRepository.save(any())).thenReturn(mockSaved);

        List<DocumentResponse> responses=documentService.uploadDocuments("R123", 1234567890L
        ,2, "Test Desc", List.of(mockFile));

        assertEquals(1,responses.size());
        assertEquals("R123",responses.get(0).getRollNumber());
        verify(documentRepository, times(1)).save(any());
    }

    @Test
    void testGetDocumentsByPrintedStatus_null(){
        Document doc= Document.builder().id(1L).printed(false).build();
        when(documentRepository.findAll()).thenReturn(List.of(doc));

        List<DocumentResponse> responses= documentService.getDocumentsByPrintedStatus(null);
        assertEquals(1,responses.size());
        verify(documentRepository, times(1)).findAll();
    }

    @Test
    void testGetDocumentsByPrintedStatus_true(){
        Document document= Document.builder().id(2L).printed(true).build();
        when(documentRepository.findByPrinted(true)).thenReturn(List.of(document));

        List<DocumentResponse> responses= documentService.getDocumentsByPrintedStatus(true);
        assertEquals(1,responses.size());
        verify(documentRepository, times(1)).findByPrinted(true);
    }

    @Test
    void testMarkAsPrinted_Success(){
        Document document= Document.builder().id(1L).printed(false).build();
        when(documentRepository.findById(1L)).thenReturn(Optional.of(document));
        when(documentRepository.save(any())).thenReturn(document);

        DocumentResponse response= documentService.markAsPrinted(1L);
        assertTrue(response.isPrinted());
        verify(documentRepository).findById(1L);
        verify(documentRepository).save(document);
    }

    @Test
    void testMarkAsPrinted_NotFound(){
        when(documentRepository.findById(99L)).thenReturn(Optional.empty());
        RuntimeException exception= assertThrows(RuntimeException.class, ()->documentService.markAsPrinted(99L));
        assertEquals("Document not found",exception.getMessage());
    }

    @Test
    void testViewDocument_Success(){
        Document doc= Document.builder().id(1L).fileName("list.pdf").build();
        when(documentRepository.findById(1L)).thenReturn(Optional.of(doc));

        DocumentViewData viewData= documentService.viewDocument(1L);
        assertEquals("list.pdf", doc.getFileName());
        verify(documentRepository).findById(1L);
    }



}