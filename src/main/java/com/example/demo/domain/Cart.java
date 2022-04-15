package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cart")
public class Cart {
    public Cart() {
        super();
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_uuid", unique = true, updatable = false)
    private UUID productUuid;
    private double totalPrice;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartItems> cartItem;
    @OneToOne
    @JoinColumn(name = "user_uuid")
    private User user;

}
