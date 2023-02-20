package com.financial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjectBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectBeApplication.class, args);
    }

}
