package com.example.demo.dto.response.warehouse;

import lombok.Data;

import java.util.UUID;

@Data
public class WarehouseCreateResponseDto {
    protected UUID warehouseUuid;
    protected String name;
    protected String code;
    protected String address;
    protected String phone;
    protected int priority;
}
