package com.example.demo.service;

import com.example.demo.domain.Orders;
import com.example.demo.dto.response.PageResponseDto;

import java.util.UUID;

public interface OrdersCRUDService extends CRUDService<Orders, UUID> {
    PageResponseDto<Orders> findAll(int page, int size, String sortType, String sortBy, String search);
}
