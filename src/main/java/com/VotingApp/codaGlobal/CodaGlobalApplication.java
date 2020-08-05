package com.VotingApp.codaGlobal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CodaGlobalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodaGlobalApplication.class, args);
	}

}
