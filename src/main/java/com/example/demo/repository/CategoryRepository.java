package com.example.demo.repository;

import com.example.demo.domain.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query(value = "select c.name, p from Category c join ProductCategory pc on pc.category.categoryUuid = c.categoryUuid " +
            "join Product p on p.productUuid = pc.product.productUuid where c.categoryUuid =?1 order by p.rating desc  ")
    List getTopFiveInCategory(UUID categoryUuid, Pageable pageable);

    @Query(value = "select c.name, p from Category c join ProductCategory pc on pc.category.categoryUuid = c.categoryUuid " +
            "join Product p on p.productUuid = pc.product.productUuid  and p.name like %?2% or p.code like %?2% where c.categoryUuid =?1 order by p.rating desc  ")
    List searchProductInCategory(UUID categoryUuid, String name, Pageable pageable);
}
