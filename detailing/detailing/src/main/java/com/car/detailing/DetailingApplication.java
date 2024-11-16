// File: com/car/detailing/DetailingApplication.java

package com.car.detailing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.car.detailing.model") // Adjust if your entity package is different
@EnableJpaRepositories("com.car.detailing.repository") // Adjust if your repository package is different
public class DetailingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DetailingApplication.class, args);
    }
}
