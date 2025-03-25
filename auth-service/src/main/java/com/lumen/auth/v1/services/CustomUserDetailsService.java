package com.lumen.auth.v1.services;


import com.lumen.auth.v1.entities.UserEntity;
import com.lumen.auth.v1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class that implements the UserDetailsService interface to load user data from the database. This class is used by Spring Security to authenticate users.
 * @author matheusdpo
 * @version 1.0.0
 * @since 2025-03
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * User repository to access the database.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Method that loads user data from the database.
     * @param username The username to be loaded.
     * @return The user details.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getAuthorities()
        );
    }
}