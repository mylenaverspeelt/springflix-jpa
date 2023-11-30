package com.projeto.springflixjpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projeto.springflixjpa.principal.Principal;
import com.projeto.springflixjpa.repository.SerieRepository;


@SpringBootApplication
public class SpringflixJpaApplication implements CommandLineRunner {
    @Autowired
    private SerieRepository repositorio;

    public static void main(String[] args) {
        SpringApplication.run(SpringflixJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal mainClass = new Principal(repositorio);
        mainClass.exibeMenu();
    }
}
