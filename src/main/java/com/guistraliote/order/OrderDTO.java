package com.guistraliote.order;

import com.guistraliote.customer.Customer;
import com.guistraliote.orderItems.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate = LocalDateTime.now();
    private String status;
    private Double totalOrderValue;
    private Customer customer;
    private List<OrderItems> orderItems = new ArrayList<>();
}
