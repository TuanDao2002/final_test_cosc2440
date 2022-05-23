package com.cosc2440.final_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cosc2440.final_test", entityManagerFactoryRef = "sessionFactoryBean")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
