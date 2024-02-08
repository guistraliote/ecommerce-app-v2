package com.guistraliote.category;

import com.guistraliote.product.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public List<CategoryDTO> findAllPaged(int index, int pageSize) {
        List<Category> categories = categoryRepository.findPaged(index, pageSize);

        return categories.stream()
                .map(category -> CategoryDTO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .isActive(category.getIsActive())
                        .path(category.getPath())
                        .productIds(category.getProducts()
                            .stream()
                            .map(Product::getId)
                            .collect(Collectors.toList()))
                .build())
                .collect(Collectors.toList());
    }
}
