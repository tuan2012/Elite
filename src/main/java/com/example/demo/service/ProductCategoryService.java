package com.example.demo.service;

import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.product.ProductResponseListDto;

import java.util.UUID;

public interface ProductCategoryService {
    PageResponseDto<ProductResponseListDto> getListProductByCategory(UUID categoryUuid, int page, int size, String sortBy, String sortType, String search);
}
