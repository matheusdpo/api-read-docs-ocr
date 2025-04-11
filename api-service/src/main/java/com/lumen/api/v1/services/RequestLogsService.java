package com.lumen.api.v1.services;

import com.lumen.commons.models.entities.ApiKeyEntity;
import com.lumen.commons.models.entities.RequestLogsEntity;
import com.lumen.commons.models.entities.UserEntity;
import com.lumen.commons.repositories.RequestLogsRepository;
import com.lumen.commons.services.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RequestLogsService {

    @Autowired
    private RequestLogsRepository requestLogsRepository;

    @Autowired
    private ApiKeyService apiKeyService;

    public void saveLog(String apiKey,
                        String endpoint,
                        HttpStatusCode httpStatusCode,
                        LocalDateTime requestDate) {

        Optional<ApiKeyEntity> apiKeyEntity = apiKeyService.findByKey(apiKey);

        if (apiKeyEntity.isEmpty()) {
            throw new RuntimeException("API Key not found");
        }

        UserEntity userEntity = apiKeyEntity.get().getUserEntity();

        RequestLogsEntity requestLogsEntity = new RequestLogsEntity();
        requestLogsEntity.setApiKeyEntity(apiKeyEntity.get());
        requestLogsEntity.setUserEntity(userEntity);
        requestLogsEntity.setEndpoint(endpoint);
        requestLogsEntity.setRequestDate(requestDate);
        requestLogsEntity.setStatusCode(httpStatusCode.toString());

        requestLogsRepository.save(requestLogsEntity);
    }

    public int countByApiKeyAndRequestDateBetween(String key, LocalDateTime startDate, LocalDateTime endDate) {
        return requestLogsRepository.countByApiKeyAndRequestDateBetween(key, startDate, endDate);
    }

    public int countByUserIdAndRequestDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return requestLogsRepository.countByUserIdAndRequestDateBetween(userId, startDate, endDate);
    }
}
