package com.example.demo.service.product.Impl;

import com.example.demo.domain.Product;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.product.ProductCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCRUDServiceImpl implements ProductCRUDService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public Page<Product> findAll(Specification specification, Pageable pageable) {
        return productRepository.findAll(specification, pageable);
    }
}
