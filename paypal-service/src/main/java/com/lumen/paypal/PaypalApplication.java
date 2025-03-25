package com.lumen.paypal;

import com.lumen.paypal.v1.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaypalApplication {

    public static void main(String[] args) {
        EnvConfig.loadEnv();
        SpringApplication.run(PaypalApplication.class, args);
    }

}