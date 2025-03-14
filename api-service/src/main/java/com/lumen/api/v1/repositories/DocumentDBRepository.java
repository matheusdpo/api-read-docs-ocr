package com.lumen.api.v1.repositories;

import com.lumen.api.v1.entities.DocumentDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DocumentDBRepository extends JpaRepository<DocumentDBEntity, Long> {

    @Query("SELECT d FROM DocumentDBEntity d WHERE d.documentType = :docType AND d.country = :country")
    Optional<DocumentDBEntity> findByDoctypeAndCountry(@Param("docType") String docType, @Param("country") String country);
}
