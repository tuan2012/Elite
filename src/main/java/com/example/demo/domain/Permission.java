package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Permission {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "permission_uuid", unique = true, updatable = false)
    private UUID permissionUuid;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;
}
