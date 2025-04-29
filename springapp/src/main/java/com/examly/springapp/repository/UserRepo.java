package com.examly.springapp.repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.examly.springapp.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
   Slice<User> findByEmail(String email, Pageable pageable);
 
    // Method to find a single user by email
    User findByEmail(String email);
    
    User findByUsername(String username);
}
