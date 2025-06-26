package com.abi.studentcart.document.repository;

import com.abi.studentcart.document.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByPrintedFalse();

    List<Document> findByPrinted(Boolean printed);
}
