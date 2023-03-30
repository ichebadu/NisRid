package com.example.week7.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String password;
}
