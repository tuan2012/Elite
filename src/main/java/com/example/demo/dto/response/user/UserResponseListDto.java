package com.example.demo.dto.response.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseListDto {
    private String name;
    private String username;
    private String email;
    private Boolean isAdmin;
    private Boolean isActive;
    private Boolean isDeleted;
    private String urlImage;
    private LocalDateTime createdDate;

}
