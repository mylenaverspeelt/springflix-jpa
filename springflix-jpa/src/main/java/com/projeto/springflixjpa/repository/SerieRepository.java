package com.projeto.springflixjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.springflixjpa.model.SeriePersonalizada;

import java.util.Optional;


@Repository
public interface SerieRepository extends JpaRepository<SeriePersonalizada, Long>{
Optional<SeriePersonalizada>  findByTituloContainingIgnoreCase(String titulo);
}
