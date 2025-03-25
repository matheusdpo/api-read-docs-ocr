package com.lumen.auth.v1.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

/**
 * Class to load environment variables from .env file
 * @author matheusdpo
 * @since 2025-03
 * @version 1.0.0
 */
@Configuration
public class EnvConfig {

    /**
     * Load environment variables from .env file
     */
    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}
