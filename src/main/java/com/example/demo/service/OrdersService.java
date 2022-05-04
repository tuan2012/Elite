package com.example.demo.service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.Orders;
import com.example.demo.dto.response.PageResponseDto;

import java.util.UUID;

public interface OrdersService {
    PageResponseDto<Orders> findAll(UUID userUuid, int page, int size, String sortType, String sortBy, String search);

    Orders createOrders(Cart cart);

    Boolean updateOrders(UUID orderUuid, String status);

    Boolean deleteOrders(UUID id);
}
