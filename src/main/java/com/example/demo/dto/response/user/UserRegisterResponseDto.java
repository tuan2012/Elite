package com.example.demo.dto.response.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRegisterResponseDto {

    private UUID userUuid;
    private String username;
    private String password;
    private String email;
    private String name;
}
