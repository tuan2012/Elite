package com.example.demo.service.productstock.Impl;

import com.example.demo.domain.ProductStock;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.ProductStockRepository;
import com.example.demo.service.productstock.ProductStockCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductStockCRUDServiceImpl implements ProductStockCRUDService {
    private final ProductStockRepository productStockRepository;

    @Override
    public ProductStock save(ProductStock productStock) {
        return productStockRepository.save(productStock);
    }

    @Override
    public ProductStock findById(UUID id) {
        return productStockRepository.findById(id).orElseThrow(() -> new NotFoundException("ProductStock not found"));
    }

    @Override
    public Page<ProductStock> findAll(Specification specification, Pageable pageable) {
        return productStockRepository.findAll(specification, pageable);
    }
}
