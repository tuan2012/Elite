package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products")
@Setter
@Getter
public class Product extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_uuid", unique = true, updatable = false)
    private UUID productUuid;
    private String name;
    private String code;
    private String shortDescription;
    private String description;
    private float price;
    private int rating;
    private int isDeleted;
    private int stock;
    @OneToMany(mappedBy = "product")
    private Set<ProductCategory> productCategories;
    @OneToMany(mappedBy = "product")
    private Set<PriceHistory> priceHistories;

    @OneToMany(mappedBy = "product")
    private Set<ProductRating> productRatings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductReview> productReviews;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<CartItems> cartItems;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<OrderDetail> orderDetails;
}
