package com.onplay.reborn.onplayreborn.dtos;

public class LoginResponseDTO {
    
    private String message;
    private boolean success;
    private String username;
    private String role;
    
    public LoginResponseDTO() {}
    
    public LoginResponseDTO(String message, boolean success, String username) {
        this.message = message;
        this.success = success;
        this.username = username;
    }
    
    public LoginResponseDTO(String message, boolean success, String username, String role) {
        this.message = message;
        this.success = success;
        this.username = username;
        this.role = role;
    }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}