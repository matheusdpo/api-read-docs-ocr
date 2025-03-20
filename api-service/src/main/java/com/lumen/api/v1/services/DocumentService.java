package com.lumen.api.v1.services;

import com.lumen.api.v1.entities.DocumentDBEntity;
import com.lumen.api.v1.repositories.DocumentDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentDBRepository documentDBRepository;

    @Value("${document.size.max}")
    private Long documentSizeMaxMb;

    public boolean isDocumentTypeAndCountry(String documentType, String country) {
        return findByDocumentTypeAndCountry(documentType, country).isPresent();
    }

    public Optional<DocumentDBEntity> findByDocumentTypeAndCountry(String documentType, String country) {
        return documentDBRepository.findByDoctypeAndCountry(documentType, country);
    }

    public boolean isDocumentSizeValid(Long size) {
        return size <= documentSizeMaxMb;
    }

    public boolean isExtensionValid(String originalFilename) {
        return originalFilename.endsWith(".jpg") ||
                originalFilename.endsWith(".jpeg") ||
                originalFilename.endsWith(".png") ||
                originalFilename.endsWith(".pdf");
    }
}