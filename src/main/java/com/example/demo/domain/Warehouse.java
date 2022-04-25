package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Warehouse extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "warehouse_uuid", unique = true, updatable = false)
    private UUID warehouseUuid;
    private String name;
    private String code;
    private String address;
    private int priority;
    @OneToMany(mappedBy = "warehouse")
    private Set<ProductStock> productStocks;
}
