package com.guistraliote.cart;

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
@Table(name = "CART")
public class Cart extends PanacheEntity {

    @Column(name = "PRODUCT_ID")
    @ManyToOne
    private Product product;

    @Column(name = "CUSTOMER_ID")
    @OneToOne
    private Customer customer;


    LocalDateTime createdAt = LocalDateTime.now();

    LocalDateTime UpdatedAt = LocalDateTime.now();
}
