package com.guistraliote.productReview;

import com.guistraliote.customer.Customer;
import com.guistraliote.product.Product;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "PRODUCT_REVIEW ")
public class ProductReview extends PanacheEntity {

    @Column(name = "PRODUCT_REVIEW")
    private String review;

    @Column(name = "REVIEW_POST_DATE")
    LocalDateTime postDate = LocalDateTime.now();

    @Column(name = "REVIEW_RATE_ID")
    private Integer reviewRate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
}
