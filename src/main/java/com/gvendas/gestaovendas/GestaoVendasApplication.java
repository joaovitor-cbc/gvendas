package com.gvendas.gestaovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.gvendas.gestaovendas.models"})
@EnableJpaRepositories(basePackages = {"com.gvendas.gestaovendas.repositorys"})
@ComponentScan(basePackages = {"com.gvendas.gestaovendas.services", "com.gvendas.gestaovendas.resources"})
@SpringBootApplication
public class GestaoVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVendasApplication.class, args);
	}

}
