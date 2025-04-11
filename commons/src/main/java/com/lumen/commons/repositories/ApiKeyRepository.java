package com.lumen.commons.repositories;


import com.lumen.commons.models.entities.ApiKeyEntity;
import com.lumen.commons.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, String> {
    Optional<ApiKeyEntity> findByKey(String key);

    List<ApiKeyEntity> findByUserEntityAndStatusKey(UserEntity userEntity, boolean statusKey);

    /**
     * Update the status of an API key
     *
     * @param key       - The API key to update
     * @param statusKey - The new status
     */
    @Modifying
    @Transactional
    @Query("UPDATE ApiKeyEntity a SET a.statusKey = :statusKey WHERE a.key = :key")
    void updateStatusKey(@Param("key") String key, @Param("statusKey") boolean statusKey);


    /**
     * Find all {@link ApiKeyEntity} by {@link UserEntity} and status key
     * @param userEntity - {@link UserEntity} to find {@link ApiKeyEntity} by it
     * @param statusKey - status key to find {@link ApiKeyEntity} by it
     * @return List of {@link ApiKeyEntity}
     */
    List<ApiKeyEntity> findAllByUserEntityAndStatusKey(UserEntity userEntity, boolean statusKey);
}