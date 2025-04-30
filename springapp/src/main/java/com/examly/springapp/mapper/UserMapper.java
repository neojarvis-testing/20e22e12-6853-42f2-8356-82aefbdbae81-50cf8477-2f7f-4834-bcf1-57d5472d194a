package com.examly.springapp.mapper;
 
import com.examly.springapp.model.User;
import com.examly.springapp.model.UserDTO;
 
public class UserMapper {
    public static User mapUserDtoToUser(UserDTO userDTO){
        return new User(
            userDTO.getEmail(),
            userDTO.getUsername(),
            userDTO.getPassword(),
            userDTO.getMobileNumber(),
            userDTO.getUserRole()
        );
    }
 
    public static UserDTO mapUserToUserDTO(User user){
        return new UserDTO(
            user.getId(),
            user.getEmail(),
            user.getUsername(),
            user.getPassword(),
            user.getMobileNumber(),
            user.getUserRole()
        );
    }
}