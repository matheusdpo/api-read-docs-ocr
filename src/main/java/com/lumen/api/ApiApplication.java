package com.lumen.api;

import com.lumen.api.v1.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		EnvConfig.loadEnv();
		SpringApplication.run(ApiApplication.class, args);
	}

}
