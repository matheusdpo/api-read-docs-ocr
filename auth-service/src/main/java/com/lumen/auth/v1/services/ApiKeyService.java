package com.lumen.auth.v1.services;

import com.lumen.auth.v1.entities.ApiKeyEntity;
import com.lumen.auth.v1.entities.UserEntity;
import com.lumen.auth.v1.repositories.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public String generateApiKey(UserEntity userEntity) {
        String apiKey = UUID.randomUUID().toString();
        ApiKeyEntity keyEntity = new ApiKeyEntity(apiKey, userEntity);
        apiKeyRepository.save(keyEntity);
        return apiKey;
    }

    public boolean validateApiKey(String apiKey) {
        Optional<ApiKeyEntity> optionalApiKey = apiKeyRepository.findByKey(apiKey);
        if (optionalApiKey.isPresent()) {
            ApiKeyEntity apiKeyEntity = optionalApiKey.get();
            return apiKeyEntity.getExpiresAt() == null || apiKeyEntity.getExpiresAt().after(new Date());
        }
        return false;
    }

    public UserEntity getUserByApiKey(String apiKey) {
        return apiKeyRepository.findByKey(apiKey)
                .map(ApiKeyEntity::getUserEntity) // Usando getUserEntity
                .orElse(null);
    }
}