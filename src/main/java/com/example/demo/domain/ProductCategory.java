package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class ProductCategory {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_category_uuid", unique = true, updatable = false)
    private UUID productCategoryUuid;
    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "category_uuid")
    private Category category;
}
