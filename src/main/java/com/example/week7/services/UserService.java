package com.example.week7.services;

import com.example.week7.dto.UserDTO;
import com.example.week7.model.User;

import java.util.List;

public interface UserService {
    public User save (UserDTO userDTO);
    UserDTO registerUser(UserDTO userDTO);
    public UserDTO Authenticate(UserDTO userDTO);
    List<UserDTO> findAllUsers();
    UserDTO mapToDTO(User user);


//    String createUser(UserDTO userDTO);
//    User findUserByEmail(String email, String password);
//    List<UserDTO> findAllUsers();
}
