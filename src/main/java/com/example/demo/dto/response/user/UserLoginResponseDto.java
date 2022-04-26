package com.example.demo.dto.response.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserLoginResponseDto {

    private UUID userUuid;
    private String username;
    private String name;
    private String accessToken;
    private String refreshToken;
}
