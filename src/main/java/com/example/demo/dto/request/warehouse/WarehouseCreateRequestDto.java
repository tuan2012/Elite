package com.example.demo.dto.request.warehouse;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WarehouseCreateRequestDto {
    @NotBlank(message = "Warehouse name is required")
    protected String name;
    @NotBlank(message = "Warehouse address is required")
    protected String address;
    @NotBlank(message = "Warehouse phone is required")
    protected String phone;
    @NotBlank(message = "Warehouse email is required")
    protected String email;
    @NotNull(message = "Warehouse priority is required")
    protected Integer priority;
}
