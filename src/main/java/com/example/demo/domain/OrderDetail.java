package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
public class OrderDetail extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_detail_uuid", unique = true, updatable = false)
    private UUID orderDetailUuid;
    @ManyToOne
    @JoinColumn(name = "order_uuid", unique = true)
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "product_uuid", unique = true)
    private Product product;
    private int quantity;
    private double totalPrice;
    @OneToOne
    @JoinColumn(name = "price_hítory_uuid")
    private PriceHistory priceHistory;
}
