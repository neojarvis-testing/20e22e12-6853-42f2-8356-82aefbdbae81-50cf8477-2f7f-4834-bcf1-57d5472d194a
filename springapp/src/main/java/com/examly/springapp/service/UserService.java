package com.examly.springapp.service;

import com.examly.springapp.model.User;
import com.examly.springapp.model.UserDTO;
import java.util.*;

public interface UserService {
    public UserDTO registerUsers(UserDTO userDTO);
    public User loginUser(User user);
    public List<UserDTO> getUsersByPagination(Integer pageNo, Integer pageSize);
    public UserDTO updateUser(int userId, UserDTO userDTO);
    public UserDTO getUserById(int userId);
    public Map<String,String> deleteUserById(int userId);
    public UserDTO getUserByUsername(String username);
}
