package com.lumen.auth;

import com.lumen.auth.v1.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		EnvConfig.loadEnv();
		SpringApplication.run(AuthApplication.class, args);
	}

}