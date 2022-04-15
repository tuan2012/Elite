package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class UserRegisterResponseDto {
    @JsonProperty("user_uuid")
    private UUID userUuid;
    private String username;
    private String password;
    private String email;
    private String name;
}
