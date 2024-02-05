package com.guistraliote.attribute;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AttributeRepository implements PanacheRepository<Attribute> {

    public PanacheQuery<Attribute> getAllActive() {
        return Attribute.find("isActive", true);
    }

    public PanacheQuery<Attribute> getAllPaged(int page, int size) {
        PanacheQuery<Attribute> attributes = Attribute.findAll();
        return attributes.page(Page.of(page,size));
    }

    public PanacheQuery<Attribute> getByName(String name) {
        return Attribute.find("attributeName", name);
    }
}
