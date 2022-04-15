package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class WareHouse {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "warehouse_uuid", unique = true, updatable = false)
    private UUID warehouseUuid;
    private String name;
    private String code;
    private String address;
    @OneToMany(mappedBy = "wareHouse")
    private Set<ProductStock> productStocks;
}
