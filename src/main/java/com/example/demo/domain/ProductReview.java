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
public class ProductReview extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_review_uuid", unique = true, updatable = false)
    private UUID productReviewUuid;
    private String value;
    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;

    @OneToMany(mappedBy = "productReview", cascade = CascadeType.ALL)
    public Set<ProductReview> productReviews;


    @ManyToOne
    @JoinColumn(name = "product_review_foreign_uuid")
    private ProductReview productReview;
}
