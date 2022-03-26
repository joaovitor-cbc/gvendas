package com.gvendas.gestaovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.gvendas.gestaovendas.models"})
@EnableJpaRepositories(basePackages = {"com.gvendas.gestaovendas.repositories"})
@ComponentScan(basePackages = {"com.gvendas.gestaovendas.config","com.gvendas.gestaovendas.resources.exception",
		"com.gvendas.gestaovendas.repositories", "com.gvendas.gestaovendas.services", "com.gvendas.gestaovendas.resources",
		"com.gvendas.gestaovendas.dtos"})
@SpringBootApplication
public class GestaoVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVendasApplication.class, args);
	}

}
