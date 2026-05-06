package com.example.umc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UmcApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmcApplication.class, args);
    }

}
