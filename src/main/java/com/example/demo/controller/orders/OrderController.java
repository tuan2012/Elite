package com.example.demo.controller.orders;

import com.example.demo.domain.Orders;
import com.example.demo.dto.request.OrdersFilterDto;
import com.example.demo.dto.response.PageUserResponseDto;
import com.example.demo.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrdersService orderService;

    @GetMapping("/all")
    public ResponseEntity<PageUserResponseDto<Orders>> getOrder(OrdersFilterDto ordersFilterDto,
                                                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                                @RequestParam(value = "size", required = false, defaultValue = "4") int size,
                                                                @RequestParam(value = "sortBy", required = false, defaultValue = "createdDate") String sortBy,
                                                                @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType) {
        return ResponseEntity.ok(orderService.findAll(page, size, sortType, sortBy, ordersFilterDto));
    }

}
