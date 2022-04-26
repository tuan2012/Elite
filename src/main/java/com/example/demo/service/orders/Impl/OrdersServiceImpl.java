package com.example.demo.service.orders.Impl;

import com.example.demo.domain.Orders;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.service.orders.OrdersService;
import com.example.demo.specifications.SpecificationBuilder;
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
    private final SpecificationBuilder specificationBuilder;

    @Override
    public PageResponseDto<Orders> findAll(int page, int size, String sortType, String sortBy, String search) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        Page<Orders> ordersPage = ordersRepository.findAll(specificationBuilder.createSpecification(search), pageable);
        PageResponseDto<Orders> pageResponseDto = new PageResponseDto<>();
        pageResponseDto.setPage(page);
        pageResponseDto.setSize(size);
        pageResponseDto.setTotalPages(ordersPage.getTotalPages());
        pageResponseDto.setTotalElements(ordersPage.getTotalElements());
        pageResponseDto.setElements(ordersPage.getContent());
        return pageResponseDto;
    }
}
