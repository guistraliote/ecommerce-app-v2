package com.guistraliote.order;

import com.guistraliote.customer.Customer;
import com.guistraliote.orderItems.OrderItems;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER_ORDER")
public class Order extends PanacheEntity {

    LocalDateTime orderDate = LocalDateTime.now();

    private String status;

    private Double totalOrderValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;

    public void addOrderItem(OrderItems orderItem) {
        if (Objects.isNull(orderItems)) {
            orderItems = new ArrayList<>();
        }
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void calculateTotalOrderValue() {
        if (Objects.nonNull(orderItems)) {

            this.totalOrderValue = orderItems.stream()
                    .filter(orderItem -> Objects.nonNull(orderItem.getProduct()))
                    .mapToDouble(orderItem -> orderItem.getProduct().getPrice() * orderItem.getQuantity())
                    .sum();
        }
    }
}
