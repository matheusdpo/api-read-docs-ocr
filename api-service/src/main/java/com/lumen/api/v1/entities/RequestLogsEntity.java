package com.lumen.api.v1.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "request_logs")
public class RequestLogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key")
    private ApiKeyEntity apiKeyEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "response_date")
    private String statusCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApiKeyEntity getApiKeyEntity() {
        return apiKeyEntity;
    }

    public void setApiKeyEntity(ApiKeyEntity apiKeyEntity) {
        this.apiKeyEntity = apiKeyEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
