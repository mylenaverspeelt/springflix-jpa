package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.SeriePersonalizada;


@Repository
public interface SerieRepository extends JpaRepository<SeriePersonalizada, Long>{

}
