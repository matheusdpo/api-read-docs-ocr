package com.lumen.api.v1.services;

import com.lumen.api.v1.entities.DocumentDBEntity;
import com.lumen.api.v1.repositories.DocumentDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentServices {

    @Autowired
    private DocumentDBRepository documentDBRepository;

    public boolean isDocumentTypeAndCountry(String documentType, String country) {
        return findByDocumentTypeAndCountry(documentType, country).isPresent();
    }

    public Optional<DocumentDBEntity> findByDocumentTypeAndCountry(String documentType, String country) {
        return documentDBRepository.findByDoctypeAndCountry(documentType, country);
    }
}