package com.lumen.auth.v1.repositories;

import com.lumen.auth.v1.entities.ApiKeyEntity;
import com.lumen.auth.v1.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, String> {
    List<ApiKeyEntity> findByUserEntity(UserEntity userEntity);

    Optional<ApiKeyEntity> findByKey(String key);
}