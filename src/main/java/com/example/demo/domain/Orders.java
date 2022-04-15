package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_uuid", unique = true, updatable = false)
    private UUID orderUuid;
    private double totalPrice;
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderDetail> orderDetail;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;
}
