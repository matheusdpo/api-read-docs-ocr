package com.lumen.mailsender;

import com.lumen.mailsender.v1.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailsenderApplication {

    public static void main(String[] args) {
        EnvConfig.loadEnv();
        SpringApplication.run(MailsenderApplication.class, args);
    }

}
