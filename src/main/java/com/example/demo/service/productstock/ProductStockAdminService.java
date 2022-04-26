package com.example.demo.service.productstock;

import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.productstock.ProductStockResponseListDto;

public interface ProductStockAdminService {
    PageResponseDto<ProductStockResponseListDto> getProductStocks(int page, int size, String sortType, String sortBy, String search);
}
