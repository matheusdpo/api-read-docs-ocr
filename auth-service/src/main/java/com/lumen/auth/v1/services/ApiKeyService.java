package com.lumen.auth.v1.services;

import com.lumen.auth.v1.entities.ApiKeyEntity;
import com.lumen.auth.v1.entities.UserEntity;
import com.lumen.auth.v1.repositories.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * A class that represents the service for the API key. It is responsible for generating, validating and retrieving the user by the API key.
 * @version 1.0.0
 * @author matheusdpo
 * @since 2025-03
 */
@Service
public class ApiKeyService {

    /**
     * Default constructor.
     */
    public ApiKeyService() {

    }

    /**
     * The repository for the API key.
     */
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    /**
     * Generates an API key for the user.
     * @param userEntity The user entity.
     * @return The API key.
     */
    public String generateApiKey(UserEntity userEntity) {
        String apiKey = UUID.randomUUID().toString();
        ApiKeyEntity keyEntity = new ApiKeyEntity(apiKey, userEntity);
        apiKeyRepository.save(keyEntity);
        return apiKey;
    }

    /**
     * Validates the API key.
     * @param apiKey The API key.
     * @return A boolean indicating if the API key is valid.
     */
    public boolean validateApiKey(String apiKey) {
        Optional<ApiKeyEntity> optionalApiKey = apiKeyRepository.findByKey(apiKey);
        if (optionalApiKey.isPresent()) {
            ApiKeyEntity apiKeyEntity = optionalApiKey.get();
            return apiKeyEntity.getExpiresAt() == null || apiKeyEntity.getExpiresAt().after(new Date());
        }
        return false;
    }

    /**
     * Retrieves the user by the API key.
     * @param apiKey The API key.
     * @return The user entity.
     */
    public UserEntity getUserByApiKey(String apiKey) {
        return apiKeyRepository.findByKey(apiKey)
                .map(ApiKeyEntity::getUserEntity) // Usando getUserEntity
                .orElse(null);
    }
}