package com.guistraliote.attribute;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class AttributeRepository implements PanacheRepository<Attribute> {

    public List<Attribute> findPaged(int pageIndex, int pageSize) {
        return findAll().page(Page.of(pageIndex, pageSize)).list();
    }
}