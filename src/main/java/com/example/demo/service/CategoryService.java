package com.example.demo.service;

import com.example.demo.dto.request.category.CategoryCreateRequestDto;
import com.example.demo.dto.response.category.CategoryCreateResponseDto;

import java.util.List;

public interface CategoryService {
     CategoryCreateResponseDto createCategory(CategoryCreateRequestDto dto);

     List<CategoryCreateResponseDto> getCategories();

}
