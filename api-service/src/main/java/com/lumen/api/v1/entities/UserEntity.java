package com.lumen.api.v1.entities;

import com.lumen.api.v1.enums.StatusBillingTypeEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Entity representing a user in the database.
 * This class is used by Spring Security to authenticate users.
 * You can add roles/authorities if necessary.
 *
 * @author matheusdpo
 * @version 1.0.0
 * @since 2025-03
 */
@Entity
@Table(name = "users")
public class UserEntity {

    /**
     * User ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User name.
     */
    @Column(name = "name")
    private String name;

    /**
     * User last name.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * User username.
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * User email.
     */
    @Column(name = "email")
    private String email;

    /**
     * User phone.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * User password.
     */
    @Column(name = "password")
    private String password;

    /**
     * User date of birth.
     */
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * User country.
     */
    @Column(name = "country")
    private String country;

    /**
     * User credit balance.
     */
    @Column(name = "credit_balance")
    private BigDecimal creditBalance = new BigDecimal("100.00");

    /**
     * User last payment date.
     */
    @Column(name = "last_payment_date")
    private LocalDate lastPaymentDate;

    /**
     * User active status.
     */
    @Column(name = "active")
    private Boolean active = true;

    /**
     * User billing type.
     */
    @Column(name = "billing_type")
    private String billingType = StatusBillingTypeEnum.PAY_AS_YOU_GO.getStatus();

    /**
     * User credit alert threshold.
     */
    @Column(name = "credit_alert_threshold")
    private BigDecimal creditAlertThreshold = new BigDecimal("100.00");

    /**
     * User API keys.
     */
    @OneToMany(mappedBy = "userEntity")
    private List<ApiKeyEntity> apiKeys;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(BigDecimal creditBalance) {
        this.creditBalance = creditBalance;
    }

    public LocalDate getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDate lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public BigDecimal getCreditAlertThreshold() {
        return creditAlertThreshold;
    }

    public void setCreditAlertThreshold(BigDecimal creditAlertThreshold) {
        this.creditAlertThreshold = creditAlertThreshold;
    }

    public List<ApiKeyEntity> getApiKeys() {
        return apiKeys;
    }

    public void setApiKeys(List<ApiKeyEntity> apiKeys) {
        this.apiKeys = apiKeys;
    }
}