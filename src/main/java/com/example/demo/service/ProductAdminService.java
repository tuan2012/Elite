package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.product.ProductResponseListDto;

public interface ProductAdminService {
    Boolean createProduct(Product product);

    PageResponseDto<ProductResponseListDto> getAllProduct(int page, int size, String sortType, String sortBy, String search);
}
