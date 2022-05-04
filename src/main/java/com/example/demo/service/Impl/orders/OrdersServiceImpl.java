package com.example.demo.service.Impl.orders;

import com.example.demo.domain.Cart;
import com.example.demo.domain.Orders;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.service.OrdersCRUDService;
import com.example.demo.service.OrdersService;
import com.example.demo.utils.ParseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersCRUDService ordersCRUDService;
    private final ModelMapper modelMapper;
    private final ParseUtils parseUtils;


    @Override
    public PageResponseDto<Orders> findAll(UUID userUuid, int page, int size, String sortType, String sortBy, String search) {
        return ordersCRUDService.findAll(userUuid, page, size, sortType, sortBy, search);
    }

    @Override
    public Orders createOrders(Cart cart) {
        Orders orders = parseUtils.parseCartToOrders(cart);
        Orders orderSaved;
        try {
            orderSaved = ordersCRUDService.save(orders);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating orders");
        }
        return orderSaved;
    }

    @Transactional
    @Override
    public Boolean updateOrders(UUID orderUuid, String status) {
        Orders orders = ordersCRUDService.findById(orderUuid);
        if (orders == null) {
            throw new RuntimeException("Orders not found");
        }
        orders.setStatus(status);
        return true;
    }


    @Override
    public Boolean deleteOrders(UUID id) {
        Orders orders = ordersCRUDService.findById(id);
        if (orders == null) {
            throw new RuntimeException("Orders not found");
        }
        orders.setIsDeleted(true);
        return true;
    }
}
