package com.cepheus.sovcombank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SovcombankApplication {

	public static void main(String[] args) {
		SpringApplication.run(SovcombankApplication.class, args);
	}

}
