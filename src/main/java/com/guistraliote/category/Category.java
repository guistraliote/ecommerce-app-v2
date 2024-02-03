package com.guistraliote.category;

import com.guistraliote.product.Product;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "CATEGORY")
public class Category extends PanacheEntity {

    @NotNull(message = "A categoria não pode ser nula")
    @Column(name = "CATEGORY_NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean active;

    @Column(name = "CATEGORY_PATH")
    private String path;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}
