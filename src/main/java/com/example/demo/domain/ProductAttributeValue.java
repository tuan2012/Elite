package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class ProductAttributeValue {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_attribute_value_uuid", unique = true, updatable = false)
    private UUID productAttributeValueUuid;

    @ManyToOne
    @JoinColumn(name = "product_attribute_uuid")
    private ProductAttribute productAttribute;
}
