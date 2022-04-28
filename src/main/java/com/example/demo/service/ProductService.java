package com.example.demo.service;

import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.product.ProductDetailResponseDto;
import com.example.demo.dto.response.product.ProductResponseListDto;

import java.util.UUID;

public interface ProductService {
    PageResponseDto<ProductResponseListDto> getAllProduct(int page, int size, String sortType, String sortBy, String search);

    ProductDetailResponseDto getProductDetail(UUID id);

    Boolean addComment(UUID id, String comment);

    Boolean addRating(UUID id, int rating);
}
