package com.examly.springapp.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.model.UserDTO;
import com.examly.springapp.service.UserServiceImpl;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.*;


@RestController
@Tag(name = "User Controller", description = "APIs for managing User services.")
public class UserController {
 
    private UserServiceImpl userService;
 
    @Autowired
    public UserController(UserServiceImpl uServiceImpl){
        this.userService=uServiceImpl;
    }
 
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @Operation(
            summary = "Register a new user",
            description = "Allows new users to register by providing necessary details such as username, email, password, and role."
        )
    @PostMapping("/api/registers")
    public ResponseEntity<UserDTO> registerUsers(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.status(201).body(userService.registerUsers(userDTO));
    }

    @Operation(
        summary = "Login user",
        description = "Authenticates a user using their username and password, and generates a JWT token."
    )

    @PostMapping("/api/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);
        User existingUser = userService.loginUser(user);
        System.out.println("User Id "+existingUser.getId());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        LoginDTO response = new LoginDTO(token, existingUser.getId(), existingUser.getUsername(), existingUser.getUserRole());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Operation(summary = "Users Sort By username", description = "Shows all the users using pagination and sorting.")
    @GetMapping("/users")
    public List<UserDTO> getUserWithPaging(@RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
 
        return userService.getUsersByPagination(pageNo,pageSize);
 
    }
    
    @Operation(summary = "Update User by User", description = "Update user profile by the user.")
    @PutMapping("/api/user/view/profile/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int userId,@RequestBody UserDTO userDTO){
    	return ResponseEntity.status(200).body(userService.updateUser(userId, userDTO));
    }
    
    @Operation(summary = "Get User by userId", description = "Fetch any particular user details by user id")
    @GetMapping("/api/user/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId){
    	return ResponseEntity.status(200).body(userService.getUserById(userId));
    }
   
    @Operation(summary = "Delete particular user profile by userid", description = "Delete a particular user profile by user.")
    @DeleteMapping("/api/{id}")
    public ResponseEntity<Map<String,String>> deleteUserById(@PathVariable int id){
    	return ResponseEntity.status(200).body(userService.deleteUserById(id));
    }
    
    @Operation(summary = "Get list of user by username", description = "Get the list of user by username.")
    @GetMapping("/api/name/{name}")
    public ResponseEntity<UserDTO> getUserByUserName(@PathVariable String name){
    	return ResponseEntity.status(200).body(userService.getUserByUsername(name));
    }
}
