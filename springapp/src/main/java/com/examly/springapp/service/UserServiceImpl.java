package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.config.UserPrinciple;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    /**
     * Constructor-based dependency injection for UserRepo and PasswordEncoder.
     * 
     * Constructor injection improves testability and ensures dependencies are properly initialized.
     *
     * @param userRepo Injected instance of UserRepo for user database operations
     * @param encoder Injected instance of PasswordEncoder for encrypting user passwords
     */
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    /**
     * Registers a new user by encoding their password before saving to the database.
     * Password encoding ensures security and prevents storing raw passwords.
     *
     * @param user User object containing registration details
     * @return The saved user entity with an encoded password
     */
    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

     /**
     * Loads user details by email for authentication purposes.
     * Used by Spring Security to authenticate users.
     *
     * @param email Email identifier for the user
     * @return UserDetails object containing authentication information
     * @throws UsernameNotFoundException If no user is found with the given email
     */

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User existingUser = userRepo.findByEmail(email);
        if (existingUser == null) {
            throw new UsernameNotFoundException("User name not found");
        }
        return UserPrinciple.build(existingUser);
    }

     /**
     * Authenticates the user by retrieving details from the database based on email.
     * If the user exists, it returns the user entity.
     *
     * @param user User object containing login credentials
     * @return The existing user entity if found, otherwise returns null
     */

    public User loginUser(User user) {
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser == null) {
            return null;
        }
        // No need to encode the password again here, as authentication should be handled separately
        return existingUser;
    }
}
