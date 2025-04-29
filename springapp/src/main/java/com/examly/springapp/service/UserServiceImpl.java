package com.examly.springapp.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
public class UserServiceImpl implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    private PasswordEncoder encoder;

    public UserDTO registerUsers(UserDTO userDTO) {
        User user=UserMapper.mapUserDtoToUser(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        user=userRepo.save(user);
        return UserMapper.mapUserToUserDTO(user);
    }

    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user=userRepo.save(user);
        return user;
    }
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User existingUser = userRepo.findByUsername(username);
        if (existingUser == null) {
            throw new UsernameNotFoundException("User name not found");
        }
        return UserPrinciple.build(existingUser);
    }

    public User loginUser(User user) {
        User existingUser = userRepo.findByUsername(user.getUsername());
        if (existingUser == null) {
            throw new UserNotFoundException("User Email Not Found");
        }
        // No need to encode the password again here
        return existingUser;
    }

        public List<User> getUsersByPagination(Integer pageNo, Integer pageSize) {
		//create pagerequest object
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("username").ascending());
        //pass it to repos
        Page<User> pagingUser = userRepo.findAll(pageRequest);
        //pagingUser.hasContent(); -- to check pages are there or not
        Sort nameSort = Sort.by("username");
        Sort emailSort = Sort.by("email");
        Sort multiSort = emailSort.and(nameSort);
        return pagingUser.getContent();
    }

	public UserDTO updateUser(int userId, UserDTO userDTO) {
		User existingUser=userRepo.findById(userId).orElse(null);
		if(existingUser==null) {
			throw new UserNotFoundException("User not found");
		}
		existingUser=UserMapper.mapUserDtoToUser(userDTO);
		existingUser.setId(userId);
		User user=userRepo.save(existingUser);
		return UserMapper.mapUserToUserDTO(user);
	}

	public UserDTO getUserById(int userId) {
		User user=userRepo.findById(userId).orElse(null);
		if(user==null) {
			throw new UserNotFoundException("User not found");
		}
		return UserMapper.mapUserToUserDTO(user);
	}

	public Map<String,String> deleteUserById(int userId) {
		if(userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
			return Map.of("message","User deleted successfully");
		}
		return Map.of("message","User with user Id : "+userId+" not found");
	}

	public UserDTO getUserByUsername(String username) {
		User user = userRepo.findByUsername(username);
		return UserMapper.mapUserToUserDTO(user);
    }
}
