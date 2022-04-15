package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class ProductReview {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_review_uuid", unique = true, updatable = false)
    private UUID productReviewUuid;
    private String value;
    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;
}
