package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_uuid", unique = true, updatable = false)
    private UUID userUuid;
    private String name;
    private String urlImage;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    private Boolean isDeleted = false;
    private Boolean isActive = true;
    @JsonProperty("last_modified_date")
    private LocalDateTime lastModifiedDate;
    @JsonProperty("created_date")
    private LocalDateTime createdDate;
    @JsonProperty("last_modified_by")
    private String lastModifiedBy;
    @JsonProperty("created_by")
    private String createdBy;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Orders> orders;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Cart cart;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "role_uuid")
    private Role role;
}
