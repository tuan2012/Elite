package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Attribute {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "attribute_uuid", unique = true, updatable = false)
    private UUID attributeUUID;

    @Column(name = "attribute_name")
    private String attributeName;

    private String code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attribute")
    private Set<ProductAttribute> productAttributes;

}
