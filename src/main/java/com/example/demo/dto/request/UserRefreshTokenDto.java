package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRefreshTokenDto {
    @JsonProperty("refresh_token")
    @NotBlank(message = "Refresh token is required")
    private String refreshToken;
}
