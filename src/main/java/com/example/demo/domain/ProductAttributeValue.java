package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
public class ProductAttributeValue extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_attribute_value_uuid", unique = true, updatable = false)
    private UUID productAttributeValueUuid;

    @ManyToOne
    @JoinColumn(name = "product_attribute_uuid")
    private ProductAttribute productAttribute;
}
