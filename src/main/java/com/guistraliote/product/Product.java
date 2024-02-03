package com.guistraliote.product;

import com.guistraliote.attribute.Attribute;
import com.guistraliote.category.Category;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "PRODUCT ")
public class Product extends PanacheEntity {

    @NotBlank(message = "O nome do produto não pode ser nulo")
    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;

    @NotNull(message = "O preço do produto não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço do produto deve ser maior que zero")
    @Column(name = "PRODUCT_PRICE")
    private Double price;

    @Column(name = "STOCK_QUANTITY")
    private Integer stockQuantity;

    @Column(name = "PRODUCT_BRAND")
    private String brand;

    @Column(name = "PRODUCT_WEIGHT")
    private Double weight;

    @Column(name = "PRODUCT_ADD_DATE")
    private Date addDate;

    @Column(name = "PRODUCT_UPDATE_DATE")
    LocalDateTime updateDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @OneToMany(
    mappedBy = "product",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    private List<Attribute> attribute = new ArrayList<>();
}
