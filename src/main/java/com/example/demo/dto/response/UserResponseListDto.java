package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseListDto {
    private String name;
    private String username;
    private String email;
    @JsonProperty("is_admin")
    private Boolean isAdmin;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("is_deleted")
    private Boolean isDeleted;
    @JsonProperty("url_image")
    private String urlImage;
    @JsonProperty("created_date")
    private LocalDateTime createdDate;

}
