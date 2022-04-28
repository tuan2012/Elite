package com.example.demo.service;

import com.example.demo.domain.Orders;
import com.example.demo.dto.response.PageResponseDto;

public interface OrdersAdminService {
    PageResponseDto<Orders> findAll(int page, int size, String sortType, String sortBy, String search);

}
