package com.lumen.billing.v1.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para variáveis de ambiente
 */
@Configuration
public class EnvConfig {

    /**
     * Carrega as variáveis de ambiente do arquivo .env
     */
    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}
