package com.lumen.commons.models.entities;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Entity that represents an API key in the database.
 * @author matheusdpo
 * @version 1.0.0
 * @since 2025-03
 */
@Entity
@Table(name = "api_keys")
public class ApiKeyEntity {

    /**
     * API key
     */
    @Id
    private String key;

    /**
     * User that owns the API key
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    /**
     * Date when the API key was created
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    /**
     * Date when the API key expires
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expires_at")
    private Date expiresAt;

    /**
     * Status of the API key
     */
    @Column(name = "status_key")
    private boolean statusKey = true;

    /**
     * Default constructor
     */
    public ApiKeyEntity() {
    }

    /**
     * Constructor with key and user
     *
     * @param key        API key
     * @param userEntity User that owns the API key
     */
    public ApiKeyEntity(String key, UserEntity userEntity) {
        this.key = key;
        this.userEntity = userEntity;
        this.createdAt = new Date();
    }

    /**
     * Get the API key
     *
     * @return API key
     */
    public String getKey() {
        return key;
    }

    /**
     * Set the API key
     *
     * @param key API key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Get the user that owns the API key
     *
     * @return User that owns the API key
     */
    public UserEntity getUserEntity() {
        return userEntity;
    }

    /**
     * Set the user that owns the API key
     *
     * @param userEntity User that owns the API key
     */
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * Get the date when the API key was created
     *
     * @return Date when the API key was created
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the date when the API key was created
     *
     * @param createdAt Date when the API key was created
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Get the date when the API key expires
     *
     * @return Date when the API key expires
     */
    public Date getExpiresAt() {
        return expiresAt;
    }

    /**
     * Set the date when the API key expires
     *
     * @param expiresAt Date when the API key expires
     */
    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * Get the status of the API key
     * @return the status of the API key
     */
    public boolean isStatusKey() {
        return statusKey;
    }

    /**
     * Set the status of the API key
     * @param statusKey the status of the API key
     */
    public void setStatusKey(boolean statusKey) {
        this.statusKey = statusKey;
    }
}