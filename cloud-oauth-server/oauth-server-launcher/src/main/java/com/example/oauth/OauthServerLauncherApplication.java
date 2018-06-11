package com.example.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@SpringBootApplication
@EnableJpaAuditing
public class OauthServerLauncherApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthServerLauncherApplication.class, args);
    }
}

