package com.guistraliote.product;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    @Inject
    EntityManager entityManager;

    public List<Product> findPaged(int pageIndex, int pageSize) {
        String jpql = "SELECT p FROM Product p " +
                "LEFT JOIN FETCH p.category " +
                "LEFT JOIN FETCH p.attributes";
        return entityManager.createQuery(jpql, Product.class)
                .setFirstResult(pageIndex * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Product findBySku(String sku) {
        return find("sku", sku).firstResult();
    }

    public Product findByTitle(String title) {
        return find("title", title).firstResult();
    }
}
