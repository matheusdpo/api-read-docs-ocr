package com.lumen.auth.v1.config;

import com.lumen.auth.v1.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Class to configure security for the application
 *
 * @author matheusdpo
 * @version 1.0.0
 * @since 2025-03
 */
@Configuration
public class SecurityConfig {

    /**
     * JWT Request Filter
     */
    private final JwtRequestFilter jwtRequestFilter;

    /**
     * Constructor
     *
     * @param jwtRequestFilter JwtRequestFilter - JWT Request Filter
     * @author matheusdpo
     * @since 2025-03
     */
    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * Configurations for security
     *
     * @param http HttpSecurity - Object to configure security
     * @return SecurityFilterChain - Filter chain for security
     * @throws Exception - Exception thrown if an error occurs
     * @author matheusdpo
     * @since 2025-03
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/register", "/api/v1/auth/login").permitAll()
                        .requestMatchers("/api/v1/auth/api-key/**").authenticated()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    /**
     * Authentication Manager Bean Configuration for Spring Security
     *
     * @param authenticationConfiguration - AuthenticationConfiguration - Configuration for authentication in Spring Security
     * @return AuthenticationManager - Authentication Manager for Spring Security
     * @throws Exception - Exception thrown if an error occurs
     * @author matheusdpo
     * @since 2025-03
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Password Encoder Bean Configuration for Spring Security
     *
     * @return PasswordEncoder - Password Encoder for Spring Security
     * @author matheusdpo
     * @since 2025-03
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuration for CORS
     *
     * @return CorsConfigurationSource - CORS configuration source
     * @author matheusdpo
     * @since 2025-03
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}