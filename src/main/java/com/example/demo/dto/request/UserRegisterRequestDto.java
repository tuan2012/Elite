package com.example.demo.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserRegisterRequestDto {

    private String username;
    private String password;
    @Email(message = "Email is not valid")
    private String email;
    private String name;
}
