package com.ohgimduir.jaray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JarayApplication {

    public static void main(String[] args) {
        SpringApplication.run(JarayApplication.class, args);
    }

}
