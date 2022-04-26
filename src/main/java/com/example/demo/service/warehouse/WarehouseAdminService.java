package com.example.demo.service.warehouse;

import com.example.demo.dto.request.warehouse.WarehouseCreateRequestDto;
import com.example.demo.dto.request.warehouse.WarehouseUpdateRequestDto;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.warehouse.WarehouseCreateResponseDto;
import com.example.demo.dto.response.warehouse.WarehouseResponseListDto;
import com.example.demo.dto.response.warehouse.WarehouseUpdateResponseDto;

public interface WarehouseAdminService {
    WarehouseCreateResponseDto createWarehouse(WarehouseCreateRequestDto requestDto);

    PageResponseDto<WarehouseResponseListDto> getAllWarehouse(int page, int size, String sortType, String sortBy, String search);

    WarehouseUpdateResponseDto update(WarehouseUpdateRequestDto requestDto);
}
