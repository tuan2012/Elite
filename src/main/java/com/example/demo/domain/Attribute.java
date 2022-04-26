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
public class Attribute extends Auditable<String> {
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
