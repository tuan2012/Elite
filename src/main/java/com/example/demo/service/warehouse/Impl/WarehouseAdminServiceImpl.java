package com.example.demo.service.warehouse.Impl;

import com.example.demo.domain.Warehouse;
import com.example.demo.dto.request.warehouse.WarehouseCreateRequestDto;
import com.example.demo.dto.request.warehouse.WarehouseUpdateRequestDto;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.warehouse.WarehouseCreateResponseDto;
import com.example.demo.dto.response.warehouse.WarehouseResponseListDto;
import com.example.demo.dto.response.warehouse.WarehouseUpdateResponseDto;
import com.example.demo.service.warehouse.WarehouseAdminService;
import com.example.demo.service.warehouse.WarehouseCRUDService;
import com.example.demo.specifications.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseAdminServiceImpl implements WarehouseAdminService {
    private final WarehouseCRUDService warehouseCRUDService;
    private final ModelMapper modelMapper;
    private final SpecificationBuilder specificationBuilder;

    @Override
    public WarehouseCreateResponseDto createWarehouse(WarehouseCreateRequestDto requestDto) {
        Warehouse warehouse = modelMapper.map(requestDto, Warehouse.class);
        Warehouse warehouseSaved = warehouseCRUDService.save(warehouse);
        return modelMapper.map(warehouseSaved, WarehouseCreateResponseDto.class);
    }

    @Override
    public PageResponseDto<WarehouseResponseListDto> getAllWarehouse(int page, int size, String sortType, String sortBy, String search) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));

        Page<Warehouse> warehousePage = warehouseCRUDService.findAll(specificationBuilder.createSpecification(search), pageable);
        PageResponseDto pageResponseDto = new PageResponseDto<>();
        pageResponseDto.setPage(page);
        pageResponseDto.setSize(size);
        pageResponseDto.setTotalPages(warehousePage.getTotalPages());
        pageResponseDto.setTotalElements(warehousePage.getNumberOfElements());
        pageResponseDto.setElements(warehousePage.stream().map(user -> modelMapper.map(user, WarehouseResponseListDto.class)).collect(Collectors.toList()));
        return pageResponseDto;
    }

    @Override
    public WarehouseUpdateResponseDto update(WarehouseUpdateRequestDto requestDto) {
        Warehouse warehouseSaved = warehouseCRUDService.findById(requestDto.getWarehouseUuid());
        warehouseSaved = modelMapper.map(requestDto, Warehouse.class);
        return modelMapper.map(warehouseCRUDService.save(warehouseSaved), WarehouseUpdateResponseDto.class);
    }
}
