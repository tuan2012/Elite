package com.example.demo.service;

import com.example.demo.domain.Orders;
import com.example.demo.dto.response.PageResponseDto;

public interface OrdersService {
    PageResponseDto<Orders> findAll(int page, int size, String sortType, String sortBy, String search);

    Boolean createOrders(Orders orders);

    Boolean updateOrders(Orders orders);

    Boolean deleteOrders(Long id);
}
