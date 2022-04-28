package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
public class ProductAttribute extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_attribute_uuid", unique = true)
    private String productAttributeUuid;
//
//    @ManyToOne
//    @JoinColumn(name = "product_uuid")
//    private Product product;

    @ManyToOne
    @JoinColumn(name = "attribute_uuid")
    private Attribute attribute;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productAttribute")
    private Set<ProductAttributeValue> productAttributeValues;
}
