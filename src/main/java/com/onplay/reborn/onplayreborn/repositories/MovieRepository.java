package com.onplay.reborn.onplayreborn.repositories;

import com.onplay.reborn.onplayreborn.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    
    @Query("SELECT m FROM Movie m WHERE LOWER(m.nome) = LOWER(:nome) AND m.ano = :ano")
    Optional<Movie> findByNomeAndAno(@Param("nome") String nome, @Param("ano") Integer ano);
    
    List<Movie> findByGeneroIgnoreCase(String genero);
    
    List<Movie> findByAno(Integer ano);
    
    @Query("SELECT m FROM Movie m WHERE LOWER(m.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Movie> findByNomeContainingIgnoreCase(@Param("nome") String nome);
    
    boolean existsByNomeIgnoreCaseAndAno(String nome, Integer ano);
}