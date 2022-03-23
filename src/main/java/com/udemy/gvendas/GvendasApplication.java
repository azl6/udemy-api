package com.udemy.gvendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.udemy.gvendas.domain"})
@EnableJpaRepositories(basePackages = {"com.udemy.gvendas.repositories"})
@ComponentScan(basePackages = {"com.udemy.gvendas.services", "com.udemy.gvendas.controllers", "com.udemy.gvendas.config"})
@SpringBootApplication
public class GvendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GvendasApplication.class, args);
	}

}
