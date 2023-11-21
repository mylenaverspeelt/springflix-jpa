package com.projeto.springflixjpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import main.MainClass;

@SpringBootApplication
public class SpringflixJpaApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(SpringflixJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MainClass mainClass = new MainClass();
		mainClass.exibeMenu();
	}
}
