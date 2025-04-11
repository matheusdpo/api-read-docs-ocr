package com.lumen.billing;

import com.lumen.commons.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
		"com.lumen.billing",
		"com.lumen.commons.models.entities"
})
@EnableJpaRepositories(basePackages = {
		"com.lumen.billing",
		"com.lumen.commons.repositories"
})
@ComponentScan(basePackages = {
		"com.lumen.billing",
		"com.lumen.commons",
})
@SpringBootApplication
public class BillingApplication {

	public static void main(String[] args) {
		EnvConfig.loadEnv();
		SpringApplication.run(BillingApplication.class, args);
	}

}
