package com.onplay.reborn.onplayreborn.mappers;

import com.onplay.reborn.onplayreborn.dtos.MovieRequestDTO;
import com.onplay.reborn.onplayreborn.dtos.MovieResponseDTO;
import com.onplay.reborn.onplayreborn.entities.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    
    public Movie toEntity(MovieRequestDTO dto) {
        if (dto == null) return null;
        
        return new Movie(
            dto.getNome(),
            dto.getDescricao(),
            dto.getGenero(),
            dto.getAno(),
            dto.getDuracao(),
            dto.getFaixaEtaria(),
            dto.getPoster(),
            dto.getLinkTrailer()
        );
    }
    
    public MovieResponseDTO toResponseDTO(Movie movie) {
        if (movie == null) return null;
        
        return new MovieResponseDTO(
            movie.getId(),
            movie.getNome(),
            movie.getDescricao(),
            movie.getGenero(),
            movie.getAno(),
            movie.getDuracao(),
            movie.getFaixaEtaria(),
            movie.getPoster(),
            movie.getLinkTrailer()
        );
    }
    
    public List<MovieResponseDTO> toResponseDTOList(List<Movie> movies) {
        return movies.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}