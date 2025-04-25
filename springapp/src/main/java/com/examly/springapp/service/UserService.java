package com.examly.springapp.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.examly.springapp.model.User;
import com.examly.springapp.model.UserDTO;

public interface UserService {
public UserDTO registerUser(UserDTO userDTO);
public UserDetails loadUserByUsername(String email);
public User loginUser(User user);
}
