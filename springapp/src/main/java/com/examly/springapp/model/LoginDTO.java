package com.examly.springapp.model;

public class LoginDTO {
    private String token;
    private String username;
    private String userRole;
    private int userId;

    public LoginDTO(String token,int userId, String username, String userRole) {
        this.token = token;
        this.username = username;
        this.userRole = userRole;
        this.userId=userId;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
}
