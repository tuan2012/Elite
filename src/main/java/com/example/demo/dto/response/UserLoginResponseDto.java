package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserLoginResponseDto {
    @JsonProperty("user_uuid")
    private UUID userId;
    private String username;
    private String name;
    private String accessToken;
    private String refreshToken;
}
