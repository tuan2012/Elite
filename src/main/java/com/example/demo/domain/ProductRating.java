package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
public class ProductRating {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_rating_uuid", unique = true, updatable = false)
    private UUID productRatingUuid;
    private int rating;
    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;

}
