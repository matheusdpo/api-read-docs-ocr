package com.lumen.billing;

import com.lumen.billing.v1.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BillingApplication {

	public static void main(String[] args) {
		EnvConfig.loadEnv();
		SpringApplication.run(BillingApplication.class, args);
	}

}
