package com.lumen.commons.services;

import com.lumen.commons.models.entities.ApiKeyEntity;
import com.lumen.commons.models.entities.UserEntity;
import com.lumen.commons.repositories.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public boolean isApiKeyValid(String apiKey) {
        return findByKey(apiKey).isPresent();
    }

    public Optional<ApiKeyEntity> findByKey(String key) {
        return apiKeyRepository.findByKey(key);
    }

    /**
     * Generates an API key for the user.
     *
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
     *
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
     *
     * @param apiKey The API key.
     * @return The user entity.
     */
    public UserEntity getUserByApiKey(String apiKey) {
        return apiKeyRepository.findByKey(apiKey)
                .map(ApiKeyEntity::getUserEntity) // Usando getUserEntity
                .orElse(null);
    }
}
