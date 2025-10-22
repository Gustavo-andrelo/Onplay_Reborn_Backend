package com.onplay.reborn.onplayreborn.resources;

import com.onplay.reborn.onplayreborn.dtos.LoginRequestDTO;
import com.onplay.reborn.onplayreborn.dtos.LoginResponseDTO;
import com.onplay.reborn.onplayreborn.dtos.UserRegistrationDTO;
import com.onplay.reborn.onplayreborn.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthResource {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = authService.login(loginRequest);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        LoginResponseDTO response = authService.register(registrationDTO);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}