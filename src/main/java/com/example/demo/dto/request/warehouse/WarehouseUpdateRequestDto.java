package com.example.demo.dto.request.warehouse;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class WarehouseUpdateRequestDto extends WarehouseCreateRequestDto {
    @NotBlank(message = "Warehouse id is required")
    private UUID warehouseUuid;
}
