package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "price_history")
@Getter
@Setter
public class PriceHistory extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "price_history_uuid", unique = true, updatable = false)
    private UUID priceHistoryUuid;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_uuid")
    private Product product;

    @OneToOne(mappedBy = "priceHistory", fetch = FetchType.LAZY)
    private OrderDetail orderDetail;

    @OneToOne(mappedBy = "priceHistory", fetch = FetchType.LAZY)
    private CartItems cartItems;
}
