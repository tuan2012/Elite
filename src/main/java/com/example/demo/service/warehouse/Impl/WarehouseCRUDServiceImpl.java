package com.example.demo.service.warehouse.Impl;

import com.example.demo.domain.Warehouse;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.warehouse.WarehouseCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarehouseCRUDServiceImpl implements WarehouseCRUDService {
    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse save(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse findById(UUID id) {
        return warehouseRepository.findById(id).orElseThrow(() -> new NotFoundException("Warehouse not found"));
    }

    @Override
    public Page<Warehouse> findAll(Specification specification, Pageable pageable) {
        return warehouseRepository.findAll(specification, pageable);
    }
}
