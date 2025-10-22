package com.onplay.reborn.onplayreborn.services;

import com.onplay.reborn.onplayreborn.dtos.LoginRequestDTO;
import com.onplay.reborn.onplayreborn.dtos.LoginResponseDTO;
import com.onplay.reborn.onplayreborn.dtos.UserRegistrationDTO;
import com.onplay.reborn.onplayreborn.entities.User;
import com.onplay.reborn.onplayreborn.mappers.UserMapper;
import com.onplay.reborn.onplayreborn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        Optional<User> userOpt = userRepository.findByUsernameOrEmail(loginRequest.getLogin());
        
        if (userOpt.isEmpty()) {
            return new LoginResponseDTO("Usuário não encontrado", false, null);
        }
        
        User user = userOpt.get();
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new LoginResponseDTO("Senha incorreta", false, null);
        }
        
        return new LoginResponseDTO("Login realizado com sucesso", true, user.getUsername());
    }
    
    public LoginResponseDTO register(UserRegistrationDTO registrationDTO) {
        if (userRepository.existsByUsername(registrationDTO.getUsername())) {
            return new LoginResponseDTO("Username já existe", false, null);
        }
        
        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            return new LoginResponseDTO("Email já existe", false, null);
        }
        
        User user = userMapper.toEntity(registrationDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        userRepository.save(user);
        
        return new LoginResponseDTO("Usuário registrado com sucesso", true, user.getUsername());
    }
}