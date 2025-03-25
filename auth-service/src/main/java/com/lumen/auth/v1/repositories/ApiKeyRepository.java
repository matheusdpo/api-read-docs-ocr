package com.lumen.auth.v1.repositories;

import com.lumen.auth.v1.entities.ApiKeyEntity;
import com.lumen.auth.v1.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for ApiKeyEntity
 * @author matheusdpo
 * @since 2025-03
 * @version 1.0.0
 */
@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, String> {
    /**
     * Find all {@link ApiKeyEntity} by {@link UserEntity}
     * @param userEntity - {@link UserEntity} to find {@link ApiKeyEntity} by it
     * @return List of {@link ApiKeyEntity}
     */
    List<ApiKeyEntity> findByUserEntity(UserEntity userEntity);

    /**
     * Find {@link ApiKeyEntity} by key
     * @param key - key to find ApiKeyEntity by it
     * @return {@link ApiKeyEntity} - ApiKeyEntity found by key
     */
    Optional<ApiKeyEntity> findByKey(String key);
}