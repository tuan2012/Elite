package com.example.demo.service.Impl.productcategory;

import com.example.demo.domain.ProductCategory;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.ProductCategoryRepository;
import com.example.demo.service.ProductCategoryCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCategoryCRUDServiceImpl implements ProductCategoryCRUDService {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory findById(UUID id) {
        return productCategoryRepository.findById(id).orElseThrow(() -> new NotFoundException("ProductCategory not found"));
    }

    @Override
    public Page<ProductCategory> findAll(Specification specification, Pageable pageable) {
        return productCategoryRepository.findAll(specification, pageable);
    }
}
