package com.lumen.commons.repositories;

import com.lumen.commons.models.entities.DocumentDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentDBRepository extends JpaRepository<DocumentDBEntity, Long> {

    @Query("SELECT d FROM DocumentDBEntity d WHERE d.documentType = :docType AND d.country = :country")
    Optional<DocumentDBEntity> findByDoctypeAndCountry(@Param("docType") String docType, @Param("country") String country);
}
