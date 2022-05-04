package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Category extends Auditable<String> {
    public Category(UUID categoryUuid) {
        this.categoryUuid = categoryUuid;
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "category_uuid", unique = true, updatable = false)
    private UUID categoryUuid;
    private String name;
    private String code;
    @OneToMany(mappedBy = "category")
    private Set<ProductCategory> productCategories;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public Set<Category> categories;


    @ManyToOne
    @JoinColumn(name = "category_foreign_uuid")
    private Category category;

    public Category() {
    }
}
