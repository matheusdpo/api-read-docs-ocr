package com.lumen.api.v1.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "api_keys")
public class ApiKeyEntity {
    @Id
    private String key;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity; // Nome do campo: userEntity

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expires_at")
    private Date expiresAt;

    public ApiKeyEntity() {
    }

    public ApiKeyEntity(String key, UserEntity userEntity) {
        this.key = key;
        this.userEntity = userEntity;
        this.createdAt = new Date(); // Inicializa o campo createdAt
    }

    // Getters e Setters

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public UserEntity getUserEntity() { // Getter e Setter devem refletir o nome do campo
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}