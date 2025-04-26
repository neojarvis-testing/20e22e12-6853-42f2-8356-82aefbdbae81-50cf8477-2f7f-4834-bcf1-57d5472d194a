package com.examly.springapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.config.UserPrinciple;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.mapper.UserMapper;
import com.examly.springapp.model.User;
import com.examly.springapp.model.UserDTO;
import com.examly.springapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    private PasswordEncoder encoder;

    public UserDTO registerUser(UserDTO userDTO) {
        logger.info("Registering user with email: {}", userDTO.getEmail());
        User user = UserMapper.mapUserDtoToUser(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        user = userRepo.save(user);
        logger.info("User successfully registered with ID: {}", user.getUserId());
        return UserMapper.mapUserToUserDTO(user);
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Loading user details by email: {}", email);
        User existingUser = userRepo.findByEmail(email);
        if (existingUser == null) {
            logger.error("User not found with email: {}", email);
            throw new UsernameNotFoundException("User name not found");
        }
        return UserPrinciple.build(existingUser);
    }

    public User loginUser(User user) {
        logger.info("Attempting login for email: {}", user.getEmail());
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser == null) {
            logger.error("User email not found: {}", user.getEmail());
            throw new UserNotFoundException("User Email Not Found");
        }
        logger.info("User successfully logged in with email: {}", user.getEmail());
        return existingUser;
    }
}
