package com.lumen.auth.v1.controller;

import com.lumen.auth.v1.utils.jwt.JwtUtils;
import com.lumen.commons.enums.StatusErrorEnum;
import com.lumen.commons.models.entities.ApiKeyEntity;
import com.lumen.commons.models.entities.UserEntity;
import com.lumen.commons.repositories.ApiKeyRepository;
import com.lumen.commons.repositories.UserEntityRepository;
import com.lumen.commons.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Controller for authentication endpoints (register, login, API key) in the application API v1
 *
 * @author matheusdpo
 * @version 1.0.0
 * @since 2025-03
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    /**
     * Default constructor
     */
    public AuthController() {
    }

    /**
     * Authentication Manager
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * User Details Service
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * JWT Utils
     */
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * User Repository
     */
    @Autowired
    private UserEntityRepository userRepository;

    /**
     * API Key Repository
     */
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    /**
     * Password Encoder
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Logger
     */
    @Autowired
    private LogUtils logger;

    /**
     * Register a new user in the application API v1 and return a JWT token for the new user to access protected endpoints in the API v1
     *
     * @param userEntity UserEntity - User entity to be registered
     * @return {@link ResponseEntity<String>} - Response with JWT token for the new user
     * @author matheusdpo
     * @since 2025-03
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserEntity userEntity) {
        //check if user already exists
        if (userRepository.findByUserName(userEntity.getUserName()).isPresent()) {
            throw new IllegalArgumentException(StatusErrorEnum.USER_EXIST.getError());
        }

        //check if email already exists
        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            throw new IllegalArgumentException(StatusErrorEnum.EMAIL_EXIST.getError());
        }

        //check if phone already exists
        if (userRepository.findByPhone(userEntity.getPhone()).isPresent()) {
            throw new IllegalArgumentException(StatusErrorEnum.PHONE_EXIST.getError());
        }

        //encode password before saving
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);

        //generate JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUserName());
        String jwt = jwtUtils.generateToken(userDetails);

        //return JWT token
        return ResponseEntity.ok(jwt);
    }

    /**
     * Login a user in the application and return a JWT token for the user to access protected endpoints in the API v1
     *
     * @param userEntity UserEntity - User entity to be logged in
     * @return {@link ResponseEntity<String>} - Response with JWT token for the user
     * @author matheusdpo
     * @since 2025-03
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity userEntity) {
        try {
            //authenticate user with username and password provided in the request body
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userEntity.getUserName(), userEntity.getPassword()
                    )
            );

            //generate JWT token for the user and return it in the response body
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUserName());
            String jwt = jwtUtils.generateToken(userDetails);

            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            logger.error("Falha na autenticação: " + e.getMessage());
            throw new RuntimeException(StatusErrorEnum.UNKOWN_ERROR.getError());
        }
    }

    /**
     * Create a new API key for the user in the application API v1 and return the new API key for the user to access protected endpoints in the API v1
     *
     * @param token String - JWT token for the user
     * @return {@link ResponseEntity<String>} - Response with the new API key for the user
     * @author matheusdpo
     * @since 2025-03
     */
    @PostMapping("/api-key")
    public ResponseEntity<String> createApiKey(@RequestHeader("Authorization") String token) {
        //check if token is valid
        if (Objects.isNull(token) || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException(StatusErrorEnum.TOKEN_INVALID.getError());
        }

        //remove Bearer prefix from token
        String jwtToken = token.substring(7);

        //validate token
        if (!jwtUtils.validateToken(jwtToken, userDetailsService.loadUserByUsername(jwtUtils.extractUsername(jwtToken)))) {
            throw new IllegalArgumentException(StatusErrorEnum.TOKEN_INVALID.getError());
        }

        //extract username from token
        String username = jwtUtils.extractUsername(jwtToken);

        //find user by username
        UserEntity userEntity = userRepository.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException(StatusErrorEnum.USER_NOT_FOUND.getError()));

        List<ApiKeyEntity> apiKeyEntityList = apiKeyRepository.findByUserEntityAndStatusKey(userEntity, true);

        if (apiKeyEntityList.size() >= 5) {
            throw new IllegalArgumentException(StatusErrorEnum.LIMIT_EXCEED.getError());
        }

        //generate random API key
        String apiKey = UUID.randomUUID().toString();

        //create API key entity and save it
        ApiKeyEntity apiKeyEntity = new ApiKeyEntity(apiKey, userEntity);
        apiKeyRepository.save(apiKeyEntity);

        //return API key
        return ResponseEntity.ok(apiKey);
    }

    /**
     * Delete an API key for the user in the application API v1
     *
     * @param key String - API key to be deleted
     * @return {@link ResponseEntity<String>} - Response with message indicating that the API key was deleted successfully
     * @author matheusdpo
     * @since 2025-03
     */
    @DeleteMapping("/api-key/{key}")
    public ResponseEntity<String> deleteApiKey(@PathVariable String key) {
        //delete API key by key
//        apiKeyRepository.deleteById(key);
        apiKeyRepository.updateStatusKey(key, false);

        //return success message
        return ResponseEntity.ok("API Key has been deleted successfully");
    }

    /**
     * Get all API keys for the user in the application API v1
     *
     * @param token String - JWT token for the user
     * @return {@link ResponseEntity<List<ApiKeyEntity>>} - Response with list of API keys for the user
     * @author matheusdpo
     * @since 2025-03
     */
    @GetMapping("/api-keys")
    public ResponseEntity<List<ApiKeyEntity>> getApiKeys(@RequestHeader("Authorization") String token) {
        //extract username from token
        String username = jwtUtils.extractUsername(token.substring(7));

        //find user by username
        UserEntity userEntity = userRepository.findByUserName(username).orElseThrow();

        //return list of API keys for the user
        return ResponseEntity.ok(apiKeyRepository.findAllByUserEntityAndStatusKey(userEntity, true));
    }
}