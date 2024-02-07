package com.guistraliote.attribute;

import com.guistraliote.product.Product;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "ATTRIBUTE")
public class Attribute extends PanacheEntity {

    @Column(name = "ATTRIBUTE_NAME")
    private String attributeName;

    @Column(name = "VALUE")
    private String attibuteValue;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
}
