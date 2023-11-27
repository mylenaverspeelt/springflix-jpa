package com.projeto.springflixjpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import main.MainClass;
import repository.SerieRepository;

@Component
@SpringBootApplication
public class SpringflixJpaApplication implements CommandLineRunner {
	@Autowired
	private SerieRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(SpringflixJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MainClass mainClass = new MainClass(repositorio);
		mainClass.exibeMenu();
	}
}
