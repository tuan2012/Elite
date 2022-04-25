package com.example.demo.dto.filter;

import lombok.Data;

import java.util.UUID;

@Data
public class OrdersFilterDto {
    private UUID orderUuid;
    private UUID userUuid;
}
