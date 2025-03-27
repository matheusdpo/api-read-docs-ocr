package com.lumen.paypal.v1.repositories;

import com.lumen.paypal.v1.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for UserEntity class to interact with the database.
 *
 * @author matheusdpo
 * @version 1.0.0
 * @since 2025-03
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Find a user by id.
     *
     * @param id the id to search for
     * @return an optional with the user if found
     */
    Optional<UserEntity> findById(Long id);

    /**
     * Find a user by username.
     *
     * @param username the username to search for
     * @return an optional with the user if found
     */
    Optional<UserEntity> findByUserName(String username);

    /**
     * Find a user by email.
     *
     * @param email the email to search for
     * @return an optional with the user if found
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Find a user by phone.
     *
     * @param phone the phone to search for
     * @return an optional with the user if found
     */
    Optional<UserEntity> findByPhone(String phone);
}
