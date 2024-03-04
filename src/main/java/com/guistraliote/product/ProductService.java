package com.guistraliote.product;

import com.guistraliote.attribute.Attribute;
import com.guistraliote.attribute.AttributeRepository;
import com.guistraliote.attribute.exceptions.AttributeNotFoundException;
import com.guistraliote.category.Category;
import com.guistraliote.category.CategoryRepository;
import com.guistraliote.category.exceptions.CategoryNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Inject
    AttributeRepository attributeRepository;

    @Inject
    CategoryRepository categoryRepository;

    @Transactional
    public Product create(ProductDTO productDTO, Set<Long> attributeIds, Long categoryId) {
        Product product = convertToEntity(productDTO);

        Category category = categoryRepository.findById(categoryId);
        if (Objects.nonNull(category)) {
            product.setCategory(category);
        } else {
            throw new CategoryNotFoundException("Categoria não encontrada para o ID: " + categoryId);
        }

        Set<Attribute> attributes = new HashSet<>();
        for (Long attributeId : attributeIds) {
            Attribute attribute = attributeRepository.findById(attributeId);
            if (Objects.nonNull(attribute)) {
                attributes.add(attribute);
            } else {
                throw new AttributeNotFoundException("Atributo não encontrado para o ID: " + attributeId);
            }
        }
        product.setAttributes(attributes);

        productRepository.persist(product);
        return product;
    }

    public List<ProductDTO> findAllPaged(int pageIndex, int pageSize) {
        List<Product> products = productRepository.findPaged(pageIndex, pageSize);

        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Product convertToEntity(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .title(productDTO.getTitle())
                .sku(productDTO.getSku())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .image(productDTO.getImage())
                .stockQuantity(productDTO.getStockQuantity())
                .brand(productDTO.getBrand())
                .weight(productDTO.getWeight())
                .addDate(LocalDateTime.now())
                .updateDate(productDTO.getUpdateDate())
                .build();
    }

    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .sku(product.getSku())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .stockQuantity(product.getStockQuantity())
                .brand(product.getBrand())
                .weight(product.getWeight())
                .updateDate(product.getUpdateDate())
                .build();
    }
}
