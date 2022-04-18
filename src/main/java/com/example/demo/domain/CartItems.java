package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class CartItems extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "cart_detail_uuid", unique = true, updatable = false)
    private UUID cartDetailUuid;
    @ManyToOne
    @JoinColumn(name = "cart_uuid")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;
    private int quantity;
    private double totalPrice;
    @OneToOne
    @JoinColumn(name = "price_history_uuid")
    private PriceHistory priceHistory;
}
