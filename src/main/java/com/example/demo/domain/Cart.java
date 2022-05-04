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
@Table(name = "cart")
public class Cart extends Auditable<String> {
    public Cart() {
        super();
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "cart_uuid", unique = true, updatable = false)
    private UUID cartUuid;
    private double totalPrice;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartItems> cartItem;
    @OneToOne
    @JoinColumn(name = "user_uuid")
    private User user;

}
