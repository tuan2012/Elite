package com.example.demo.dto.filter;

import lombok.Data;

@Data
public class UserFilterDto {
    private String name;
    private String email;
    private String username;
    private Boolean active;
    private Boolean deleted;
}
