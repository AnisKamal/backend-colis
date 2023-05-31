package com.colis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class ColisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColisApplication.class, args);
    }

}
