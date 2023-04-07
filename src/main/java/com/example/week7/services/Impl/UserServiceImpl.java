package com.example.week7.services.Impl;

import com.example.week7.Repo.UserRepository;
import com.example.week7.dto.UserDTO;
import com.example.week7.model.User;
import com.example.week7.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User save (UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        return userRepository.save(user);
    }

    public UserDTO registerUser(UserDTO userDTO) {
        System.out.println(userDTO);
        Optional<User> existingUser = userRepository.findUserByEmailAndPassword(userDTO.getEmail(),userDTO.getPassword());
        System.out.println(existingUser);
        if (userDTO.getEmail() == null || userDTO.getPassword() == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exist");
        }
        else {
            User user = save(userDTO);
            return mapToDTO(user);
        }
    }
    public UserDTO Authenticate(UserDTO userDTO){

        Optional<User> authenticatingUser = userRepository.findUserByEmailAndPassword(userDTO.getEmail(),userDTO.getPassword());
        if(userDTO.getEmail() == null || userDTO.getPassword() == null){
            throw new RuntimeException("cant empty blank");
        }
        if(!authenticatingUser.isPresent()){
            throw new RuntimeException("wrong password or email");
        }else{
            User user = new User();
            return mapToDTO(user);
        }
    }
    public UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = mapToDTO(user);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
}