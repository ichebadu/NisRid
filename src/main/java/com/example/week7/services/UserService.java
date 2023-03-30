package com.example.week7.services;

import com.example.week7.Repo.UserRepository;
import com.example.week7.dto.UserDTO;
import com.example.week7.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public String createUser(UserDTO userDTO){
        String status;
        if (userDTO.getEmail()==null && userDTO.getPassword()==null){
            status="username and password is empty";
        }else{
            if (findUserByEmail(userDTO.getEmail(),userDTO.getPassword())!=null){
                status="email already exist";
            }else{
                User user = new User();
                user.setUsername(userDTO.getUsername());
                user.setEmail(userDTO.getEmail());
                user.setPassword(userDTO.getPassword());
                repository.save(user);
                status="successful created account";
            }

        }
        return status;
    }

    public User findUserByEmail(String email,String password){
        return repository.findByEmailAndPassword(email,password).orElse(null);

    }
}
