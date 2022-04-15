package com.example.demo.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequestDto {

    private String username;
    private String password;
    private String email;
    private String name;
}
