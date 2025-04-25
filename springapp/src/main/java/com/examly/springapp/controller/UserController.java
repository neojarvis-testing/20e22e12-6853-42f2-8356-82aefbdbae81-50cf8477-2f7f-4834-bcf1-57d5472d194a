package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.model.UserDTO;
import com.examly.springapp.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
public class UserController {

    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    /**
     * Constructor-based dependency injection ensures proper initialization of dependencies.
     * This approach improves testability and avoids direct field injection.
     *
     * @param userService Injected instance of UserServiceImpl for user management
     * @param authenticationManager Injected instance of AuthenticationManager for handling authentication
     * @param jwtUtils Injected instance of JwtUtils for generating JWT tokens
     */
    public UserController(UserServiceImpl userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }
    /**
     * Handles user registration by accepting user details.
     * Encodes passwords before saving to ensure security.
     *
     * @param user User object containing registration details
     * @return ResponseEntity containing the registered user details with HTTP status 201 (Created)
     */
    @PostMapping("/api/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.status(201).body(userService.registerUser(userDTO));
    }

     /**
     * Authenticates the user, verifies credentials, and generates a JWT token.
     * If authentication fails, returns an Unauthorized response.
     *
     * @param user Contains email and password for authentication
     * @return ResponseEntity containing a JWT token and user details if authentication is successful,
     *         or an error message with HTTP status 401 (Unauthorized) if authentication fails.
     */

    @PostMapping("/api/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
         // Authenticate user with email and password using Spring Security

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        // Set authentication details in security context

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token for authenticated user

        String token = jwtUtils.generateToken(authentication);

        // Fetch user details from database

        User existingUser = userService.loginUser(user);
        System.out.println("User Id "+existingUser.getUserId());

        // If authentication fails, return an Unauthorized response

        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        // Create response containing JWT token and user details
        
        LoginDTO response = new LoginDTO(token, existingUser.getUserId(), existingUser.getUsername(), existingUser.getUserRole());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
