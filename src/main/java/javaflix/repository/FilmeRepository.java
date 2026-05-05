package javaflix.repository;

import javaflix.entity.Categoria;
import javaflix.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByCategoriasIn(List<Categoria> categorias);

    List<Filme> findTop5ByOrderByRatingDesc();

}
