package com.lumen.auth.v1.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * Entity representing a user in the database.
 * This class is used by Spring Security to authenticate users.
 * You can add roles/authorities if necessary.
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
    private String name;

    /**
     * User last name.
     */
    private String lastName;

    /**
     * User username.
     */
    private String username;

    /**
     * User email.
     */
    private String email;

    /**
     * User phone.
     */
    private String phone;

    /**
     * User password.
     */
    private String password;

    /**
     * User date of birth.
     */
    private String dateOfBirth;

    /**
     * User country.
     */
    private String country;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    // Adicione roles/autoridades, se necessário
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Retorne as roles do usuário, se houver
    }
}