package com.example.demo.service.Impl.orders;

import com.example.demo.domain.Orders;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.service.OrdersAdminService;
import com.example.demo.service.OrdersCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersAdminServiceImpl implements OrdersAdminService {
    private final OrdersCRUDService ordersCRUDService;

    @Override
    public PageResponseDto<Orders> findAll(int page, int size, String sortType, String sortBy, String search) {
        return ordersCRUDService.findAll(null, page, size, sortType, sortBy, search);
    }
}
