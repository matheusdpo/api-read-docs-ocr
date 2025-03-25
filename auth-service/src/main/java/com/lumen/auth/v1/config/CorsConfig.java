package com.lumen.auth.v1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class to configure CORS for the application
 * @author matheusdpo
 * @since 2025-03
 * @version 1.0.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configuração de CORS
     * @param registry CorsRegistry - CORS registry object to configure CORS for the application
     * @author matheusdpo
     * @since 2025-03
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://192.168.1.15:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}