package com.example.demo.service.Impl;

import com.example.demo.domain.Orders;
import com.example.demo.dto.request.OrdersFilterDto;
import com.example.demo.dto.response.PageUserResponseDto;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.service.OrdersService;
import com.example.demo.specificaitons.OrderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;

    @Override
    public PageUserResponseDto<Orders> findAll(int page, int size, String sortType, String sortBy, OrdersFilterDto ordersFilterDto) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        ;
        Page<Orders> ordersPage = ordersRepository.findAll(new OrderSpecification(ordersFilterDto), pageable);
        PageUserResponseDto<Orders> pageUserResponseDto = new PageUserResponseDto<>();
        pageUserResponseDto.setPage(page);
        pageUserResponseDto.setSize(size);
        pageUserResponseDto.setTotalPages(ordersPage.getTotalPages());
        pageUserResponseDto.setTotalElements(ordersPage.getTotalElements());
        pageUserResponseDto.setElements(ordersPage.getContent());
        return pageUserResponseDto;
    }
}
