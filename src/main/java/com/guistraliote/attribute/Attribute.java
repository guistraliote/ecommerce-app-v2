package com.guistraliote.attribute;

import com.guistraliote.product.Product;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ATTRIBUTE_NAME")
    private String attributeName;

    @Column(name = "VALUE")
    private String attributeValue;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @ManyToMany(mappedBy = "attributes", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
}