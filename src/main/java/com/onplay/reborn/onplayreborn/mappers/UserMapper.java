package com.onplay.reborn.onplayreborn.mappers;

import com.onplay.reborn.onplayreborn.dtos.UserRegistrationDTO;
import com.onplay.reborn.onplayreborn.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public User toEntity(UserRegistrationDTO dto) {
        if (dto == null) return null;
        
        return new User(
            dto.getUsername(),
            dto.getEmail(),
            dto.getPassword()
        );
    }
    
    public UserRegistrationDTO toDTO(User user) {
        if (user == null) return null;
        
        return new UserRegistrationDTO(
            user.getUsername(),
            user.getEmail(),
            user.getPassword()
        );
    }
}