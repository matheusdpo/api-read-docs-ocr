package com.lumen.auth.v1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todos os endpoints
                .allowedOrigins("http://192.168.1.15:3000") // Permite o frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("*") // Headers permitidos
                .allowCredentials(true); // Permite credenciais (cookies, tokens, etc.)
    }
}