package com.example.demo.dto.request.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRefreshTokenDto {
    @NotBlank(message = "Refresh token is required")
    private String refreshToken;
}
