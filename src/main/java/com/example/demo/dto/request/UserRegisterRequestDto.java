package com.example.demo.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserRegisterRequestDto {

    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
