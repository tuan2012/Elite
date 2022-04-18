package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class ProductStock extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_stock_uuid", unique = true, updatable = false)
    private UUID productStockUuid;
    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "warehoue_uuid")
    private WareHouse wareHouse;

    private int stock;

}
