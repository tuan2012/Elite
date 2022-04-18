package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Category extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "category_uuid", unique = true, updatable = false)
    private UUID categoryUuid;
    private String name;
    private String code;
    @OneToMany(mappedBy = "category")
    private Set<ProductCategory> productCategories;

}
