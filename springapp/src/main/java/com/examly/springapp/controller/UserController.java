package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;

@RestController
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl uServiceImpl){
        this.userService=uServiceImpl;
    }
    @PostMapping("/api/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return ResponseEntity.status(201).body(userService.registerUser(user));
    }
}
