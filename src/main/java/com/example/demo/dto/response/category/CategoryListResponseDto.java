package com.example.demo.dto.response.category;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryListResponseDto {
    private UUID categoryUuid;
    private String name;
    private String code;
}
