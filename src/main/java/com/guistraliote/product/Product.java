package com.guistraliote.product;

import com.guistraliote.attribute.Attribute;
import com.guistraliote.category.Category;
import com.guistraliote.productReview.ProductReview;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título do produto não pode ser nulo")
    @Column(name = "TITLE")
    private String title;

    @NotBlank(message = "O SKU não pode ser nulo")
    @Column(name = "SKU")
    private String sku;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull(message = "O preço do produto não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço do produto deve ser maior que zero")
    @Column(name = "PRICE")
    private Double price;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "STOCK_QUANTITY")
    private Integer stockQuantity;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "ADD_DATE")
    LocalDateTime addDate = LocalDateTime.now();

    @Column(name = "UPDATE_DATE")
    LocalDateTime updateDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<Attribute> attributes = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductReview> reviews = new ArrayList<>();
}
