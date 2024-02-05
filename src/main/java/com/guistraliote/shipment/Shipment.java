package com.guistraliote.shipment;

import com.guistraliote.customer.Customer;
import com.guistraliote.customerAddress.CustomerAddress;
import com.guistraliote.order.Order;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SHIPMENT")
public class Shipment extends PanacheEntity {

    @OneToOne
    @Column(name = "CUSTOMER_ID")
    private Customer customer;

    @OneToOne
    @Column(name = "CUSTOMER_ADDRESS_ID")
    private CustomerAddress customerAddress;

    @OneToOne
    @Column(name = "ORDER_ID")
    private Order order;

    @Column(name = "CREATED_AT")
    LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UPDATED_AT")
    LocalDateTime UpdatedAt = LocalDateTime.now();
}
