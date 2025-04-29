package com.examly.springapp.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public class LoginDTO {
    @NotBlank(message = "Token cannot be empty")
    private String token;
    @NotNull(message = "User ID cannot be null")
    private int userId;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "User role cannot be empty")
    @Pattern(regexp = "^(ADMIN|USER)$", message = "Invalid user role")
    private String userRole;

    public LoginDTO(String token, int userId, String username, String userRole) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.userRole = userRole;
    }
    

    public LoginDTO() {
    }


    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}