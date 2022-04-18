package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Role extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "role_uuid", unique = true, updatable = false)
    private UUID roleUuid;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Set<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Set<RoleDetail> roleDetails;

}
