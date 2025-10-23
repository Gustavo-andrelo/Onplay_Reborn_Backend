package com.onplay.reborn.onplayreborn.resources;

import com.onplay.reborn.onplayreborn.dtos.MovieRequestDTO;
import com.onplay.reborn.onplayreborn.dtos.MovieResponseDTO;
import com.onplay.reborn.onplayreborn.entities.User;
import com.onplay.reborn.onplayreborn.repositories.UserRepository;
import com.onplay.reborn.onplayreborn.services.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*")
public class MovieResource {
    
    @Autowired
    private MovieService movieService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping
    public ResponseEntity<?> cadastrarFilme(@Valid @RequestBody MovieRequestDTO movieRequest, HttpServletRequest request) {
        // Verificar autenticação
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization required");
        }
        
        try {
            String base64Credentials = authHeader.substring("Basic ".length());
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] parts = credentials.split(":", 2);
            
            if (parts.length != 2) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials format");
            }
            
            String login = parts[0];
            String password = parts[1];
            
            Optional<User> userOpt = userRepository.findByUsernameOrEmail(login);
            if (userOpt.isEmpty() || !passwordEncoder.matches(password, userOpt.get().getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
            
            MovieResponseDTO response = movieService.cadastrarFilme(movieRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> listarFilmes() {
        List<MovieResponseDTO> movies = movieService.listarTodosFilmes();
        return ResponseEntity.ok(movies);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<MovieResponseDTO> movie = movieService.buscarPorId(id);
        return movie.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<MovieResponseDTO>> buscarPorGenero(@PathVariable String genero) {
        List<MovieResponseDTO> movies = movieService.buscarPorGenero(genero);
        return ResponseEntity.ok(movies);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<MovieResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<MovieResponseDTO> movies = movieService.buscarPorNome(nome);
        return ResponseEntity.ok(movies);
    }
    
    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<MovieResponseDTO>> buscarPorAno(@PathVariable Integer ano) {
        List<MovieResponseDTO> movies = movieService.buscarPorAno(ano);
        return ResponseEntity.ok(movies);
    }
}