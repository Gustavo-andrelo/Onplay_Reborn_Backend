package com.onplay.reborn.onplayreborn.resources;

import com.onplay.reborn.onplayreborn.dtos.MovieRequestDTO;
import com.onplay.reborn.onplayreborn.dtos.MovieResponseDTO;
import com.onplay.reborn.onplayreborn.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MovieResource {
    
    @Autowired
    private MovieService movieService;
    
    // Endpoints públicos (catalog)
    @GetMapping("/catalog")
    public ResponseEntity<List<MovieResponseDTO>> getCatalog() {
        return ResponseEntity.ok(movieService.listarTodosFilmes());
    }
    
    @GetMapping("/catalog/{id}")
    public ResponseEntity<MovieResponseDTO> getCatalogMovie(@PathVariable Long id) {
        Optional<MovieResponseDTO> movie = movieService.buscarPorId(id);
        return movie.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/catalog/search")
    public ResponseEntity<List<MovieResponseDTO>> searchCatalog(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Integer ano) {
        
        if (nome != null) return ResponseEntity.ok(movieService.buscarPorNome(nome));
        if (genero != null) return ResponseEntity.ok(movieService.buscarPorGenero(genero));
        if (ano != null) return ResponseEntity.ok(movieService.buscarPorAno(ano));
        
        return ResponseEntity.ok(movieService.listarTodosFilmes());
    }
    
    // Endpoints protegidos (movies - admin)
    @PostMapping("/movies")
    public ResponseEntity<MovieResponseDTO> cadastrarFilme(@Valid @RequestBody MovieRequestDTO movieRequest) {
        MovieResponseDTO response = movieService.cadastrarFilme(movieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponseDTO>> listarFilmes() {
        return ResponseEntity.ok(movieService.listarTodosFilmes());
    }
    
    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<MovieResponseDTO> movie = movieService.buscarPorId(id);
        return movie.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}