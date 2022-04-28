package com.example.demo.service.Impl.orders;

import com.example.demo.domain.Orders;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.service.OrdersCRUDService;
import com.example.demo.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersCRUDService ordersCRUDService;


    @Override
    public PageResponseDto<Orders> findAll(int page, int size, String sortType, String sortBy, String search) {

        return ordersCRUDService.findAll(page, size, sortType, sortBy, search);
    }

    @Override
    public Boolean createOrders(Orders orders) {
        return null;
    }

    @Override
    public Boolean updateOrders(Orders orders) {
        return null;
    }

    @Override
    public Boolean deleteOrders(Long id) {
        return null;
    }
}
