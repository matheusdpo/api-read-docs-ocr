package com.lumen.auth.v1.repositories;

import com.lumen.auth.v1.entities.ApiKeyEntity;
import com.lumen.auth.v1.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Repository for ApiKeyEntity
 * @author matheusdpo
 * @version 1.0.0
 * @since 2025-03
 */
@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, String> {
    /**
     * Find all {@link ApiKeyEntity} by {@link UserEntity}
     *
     * @param userEntity - {@link UserEntity} to find {@link ApiKeyEntity} by it
     * @return List of {@link ApiKeyEntity}
     */
    List<ApiKeyEntity> findByUserEntity(UserEntity userEntity);

    /**
     * Find {@link ApiKeyEntity} by key
     *
     * @param key - key to find ApiKeyEntity by it
     * @return {@link ApiKeyEntity} - ApiKeyEntity found by key
     */
    Optional<ApiKeyEntity> findByKey(String key);

    /**
     * Find all {@link ApiKeyEntity} by {@link UserEntity} and status key
     * @param userEntity - {@link UserEntity} to find {@link ApiKeyEntity} by it
     * @param statusKey - status key to find {@link ApiKeyEntity} by it
     * @return List of {@link ApiKeyEntity}
     */
    List<ApiKeyEntity> findByUserEntityAndStatusKey(UserEntity userEntity, String statusKey);

    /**
     * Update the status of an API key
     *
     * @param key       - The API key to update
     * @param statusKey - The new status
     */
    @Modifying
    @Transactional
    @Query("UPDATE ApiKeyEntity a SET a.statusKey = :statusKey WHERE a.key = :key")
    void updateStatusKey(@Param("key") String key, @Param("statusKey") String statusKey);


    /**
     * Find all {@link ApiKeyEntity} by {@link UserEntity} and status key
     * @param userEntity - {@link UserEntity} to find {@link ApiKeyEntity} by it
     * @param statusKey - status key to find {@link ApiKeyEntity} by it
     * @return List of {@link ApiKeyEntity}
     */
    List<ApiKeyEntity> findAllByUserEntityAndStatusKey(UserEntity userEntity, String statusKey);
}