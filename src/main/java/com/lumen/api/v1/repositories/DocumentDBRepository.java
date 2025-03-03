package com.lumen.api.v1.repositories;

import com.lumen.api.v1.models.db.DocumentDBModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DocumentDBRepository extends JpaRepository<DocumentDBModel, Long> {

    @Query("SELECT d FROM DocumentDBModel d WHERE d.documentType = :docType AND d.country = :country")
    Optional<DocumentDBModel> findByDoctypeAndCountry(@Param("docType") String docType, @Param("country") String country);
}
