package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_uuid", unique = true, updatable = false)
    private UUID userUuid;
    private String name;
    private String urlImage;
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Orders> orders;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Permission> permissions;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_uuid")
    private Role role;
}
