package com.guistraliote.productReview;

import com.guistraliote.customer.Customer;
import com.guistraliote.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "PRODUCT_REVIEW")
public class ProductReview{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REVIEW")
    private String review;

    @Column(name = "POST_DATE")
    private LocalDateTime postDate = LocalDateTime.now();

    @Column(name = "REVIEW_RATE")
    private Integer reviewRate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
