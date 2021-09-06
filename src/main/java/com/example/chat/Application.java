package com.example.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.chat.service")
@EnableJpaRepositories(basePackages = "com.example.chat.repositories")
@EntityScan(basePackages = "com.example.chat.service")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
