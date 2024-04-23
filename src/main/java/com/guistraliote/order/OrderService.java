package com.guistraliote.order;

import com.guistraliote.category.exceptions.CategoryNotFoundException;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    ModelMapper mapper;

    public List<OrderDTO> findAllPaged (int index, int pageSize) {
        List<Order> orders = orderRepository.findPaged(index, pageSize);

        return orders.stream()
                .map(order -> mapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public OrderDTO findById(Long id) {
        Optional<Order> order = Optional.ofNullable(orderRepository.findByIdOptional(id))
                .orElseThrow(() -> new CategoryNotFoundException("Order not found for ID: " + id));

        return mapper.map(order, OrderDTO.class);
    }
}
