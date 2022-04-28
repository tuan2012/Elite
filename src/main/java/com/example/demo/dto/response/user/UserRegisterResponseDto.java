package com.example.demo.dto.response.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRegisterResponseDto {

    protected UUID userUuid;
    protected String username;
    protected String password;
    protected String email;
    protected String name;
}
