package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_uuid", unique = true, updatable = false)
    private UUID productUuid;
    private String name;
    private String code;
    private String description;

    private int rating;
    private int isDeleted;
    @OneToMany(mappedBy = "product")
    private Set<ProductCategory> productCategories;
    @OneToMany(mappedBy = "product")
    private Set<PriceHistory> priceHistories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductAttribute> productAttributes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductStock> productStocks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductReview> productReviews;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<CartItems> cartItems;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<OrderDetail> orderDetails;
}
