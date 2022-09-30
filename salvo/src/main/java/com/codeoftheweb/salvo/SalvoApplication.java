package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Player("Jack", "Bauer","test@test.com"));
			repository.save(new Player("Chloe", "O'Brian","test1@test.com"));
			repository.save(new Player("Kim", "Bauer","test2@test.com"));
			repository.save(new Player("David", "Palmer","test3@test.com"));
			repository.save(new Player("Michelle", "Dessler","test4@test.com"));
		};
	}
}
