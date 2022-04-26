package com.example.demo.repository;

import com.example.demo.domain.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ProductStockRepository extends JpaRepository<ProductStock, UUID>, JpaSpecificationExecutor<ProductStock> {
}
