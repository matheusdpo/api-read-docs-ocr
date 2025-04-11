package com.lumen.commons.repositories;

import com.lumen.commons.models.entities.RequestLogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface RequestLogsRepository extends JpaRepository<RequestLogsEntity, Long> {

    @Transactional
    @Query("SELECT COUNT(r) FROM RequestLogsEntity r WHERE r.apiKeyEntity.key = ?1 AND r.requestDate BETWEEN ?2 AND ?3")
    int countByApiKeyAndRequestDateBetween(String key, LocalDateTime startDate, LocalDateTime endDate);

    @Transactional
    @Query("SELECT COUNT(r) FROM RequestLogsEntity r WHERE r.userEntity.id = ?1 AND r.requestDate BETWEEN ?2 AND ?3")
    int countByUserIdAndRequestDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}