package com.onplay.reborn.onplayreborn.resources;

import com.onplay.reborn.onplayreborn.dtos.MovieResponseDTO;
import com.onplay.reborn.onplayreborn.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalog")
@CrossOrigin(origins = "*")
public class CatalogResource {
    
    @Autowired
    private MovieService movieService;
    
    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getCatalog() {
        List<MovieResponseDTO> movies = movieService.listarTodosFilmes();
        return ResponseEntity.ok(movies);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable Long id) {
        Optional<MovieResponseDTO> movie = movieService.buscarPorId(id);
        return movie.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponseDTO>> searchMovies(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Integer ano) {
        
        if (nome != null) {
            return ResponseEntity.ok(movieService.buscarPorNome(nome));
        }
        if (genero != null) {
            return ResponseEntity.ok(movieService.buscarPorGenero(genero));
        }
        if (ano != null) {
            return ResponseEntity.ok(movieService.buscarPorAno(ano));
        }
        
        return ResponseEntity.ok(movieService.listarTodosFilmes());
    }
}