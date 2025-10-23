package com.onplay.reborn.onplayreborn.services;

import com.onplay.reborn.onplayreborn.dtos.MovieRequestDTO;
import com.onplay.reborn.onplayreborn.dtos.MovieResponseDTO;
import com.onplay.reborn.onplayreborn.entities.Movie;
import com.onplay.reborn.onplayreborn.mappers.MovieMapper;
import com.onplay.reborn.onplayreborn.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private MovieMapper movieMapper;
    
    public MovieResponseDTO cadastrarFilme(MovieRequestDTO movieRequest) {
        // Verificar se filme já existe (mesmo nome e ano)
        if (movieRepository.existsByNomeIgnoreCaseAndAno(movieRequest.getNome(), movieRequest.getAno())) {
            throw new RuntimeException("Filme já cadastrado com este nome e ano");
        }
        
        Movie movie = movieMapper.toEntity(movieRequest);
        Movie savedMovie = movieRepository.save(movie);
        
        return movieMapper.toResponseDTO(savedMovie);
    }
    
    public List<MovieResponseDTO> listarTodosFilmes() {
        List<Movie> movies = movieRepository.findAll();
        return movieMapper.toResponseDTOList(movies);
    }
    
    public Optional<MovieResponseDTO> buscarPorId(Long id) {
        return movieRepository.findById(id)
                .map(movieMapper::toResponseDTO);
    }
    
    public List<MovieResponseDTO> buscarPorGenero(String genero) {
        List<Movie> movies = movieRepository.findByGeneroIgnoreCase(genero);
        return movieMapper.toResponseDTOList(movies);
    }
    
    public List<MovieResponseDTO> buscarPorNome(String nome) {
        List<Movie> movies = movieRepository.findByNomeContainingIgnoreCase(nome);
        return movieMapper.toResponseDTOList(movies);
    }
    
    public List<MovieResponseDTO> buscarPorAno(Integer ano) {
        List<Movie> movies = movieRepository.findByAno(ano);
        return movieMapper.toResponseDTOList(movies);
    }
}