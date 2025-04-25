package com.examly.springapp.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @NotBlank
    @Size(min=10,message="Mobile number must be at least 10 numbers")
    private String mobileNumber;
    @NotBlank(message = "User role is required")
    @Pattern(regexp = "^(ADMIN|USER)$", message = "Invalid user role")
    private String userRole;
      // Constructors
    public UserDTO() {
    }
    
    

    public UserDTO(@Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email,
            @NotBlank(message = "Username cannot be empty") String username,
            @NotBlank(message = "Password cannot be empty") @Size(min = 8, message = "Password must be at least 8 characters long") String password,
            @NotBlank @Size(min = 10, message = "Mobile number must be at least 10 numbers") String mobileNumber,
            @NotBlank(message = "User role is required") String userRole) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.userRole = userRole;
    }



    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
