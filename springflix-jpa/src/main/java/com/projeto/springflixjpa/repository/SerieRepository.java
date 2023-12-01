package com.projeto.springflixjpa.repository;

import com.projeto.springflixjpa.principal.GenerosEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.springflixjpa.model.SeriePersonalizada;

import java.util.List;
import java.util.Optional;

//usar o nome do atributo quando for fazer uma derived querie, não o nome da coluna da tabela
@Repository
public interface SerieRepository extends JpaRepository<SeriePersonalizada, Long> {
    Optional<SeriePersonalizada> findByTituloContainingIgnoreCase(String titulo);

    List<SeriePersonalizada> findByAtoresContainingIgnoreCase(String nomeAtor);

    List<SeriePersonalizada> findByAvaliacaoGreaterThanEqual(Double media);

    List<SeriePersonalizada> findTop5ByOrderByAvaliacaoDesc();

    List<SeriePersonalizada> findByGenero(GenerosEnum genero);
}
