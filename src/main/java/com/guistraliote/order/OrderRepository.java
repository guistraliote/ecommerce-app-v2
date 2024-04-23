package com.guistraliote.order;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;

import java.util.List;

public class OrderRepository implements PanacheRepository<Order> {
    public List<Order> findPaged(int index, int pageSize) {
        return findAll().page(Page.of(index, pageSize)).list();
    }
}
