package com.lumen.billing.v1.repositories;


import com.lumen.billing.v1.entities.ApiKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, String> {
    Optional<ApiKeyEntity> findByKey(String key);
}