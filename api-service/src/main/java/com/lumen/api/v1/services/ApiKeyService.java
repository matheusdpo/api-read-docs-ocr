package com.lumen.api.v1.services;

import com.lumen.api.v1.entities.ApiKeyEntity;
import com.lumen.api.v1.repositories.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
