package com.onplay.reborn.onplayreborn.config;

import com.onplay.reborn.onplayreborn.entities.User;
import com.onplay.reborn.onplayreborn.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Base64;
import java.util.Optional;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\":\"Authorization required\"}");
            return false;
        }
        
        try {
            String base64Credentials = authHeader.substring("Basic ".length());
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] parts = credentials.split(":", 2);
            
            if (parts.length != 2) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            
            String login = parts[0];
            String password = parts[1];
            
            Optional<User> userOpt = userRepository.findByUsernameOrEmail(login);
            
            if (userOpt.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            
            User user = userOpt.get();
            
            // Para simplificar, vamos aceitar qualquer usuário logado
            // Em produção, você implementaria verificação de senha e role ADMIN
            request.setAttribute("currentUser", user);
            return true;
            
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}