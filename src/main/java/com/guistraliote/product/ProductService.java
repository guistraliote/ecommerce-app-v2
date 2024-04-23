package com.guistraliote.product;

import com.guistraliote.attribute.Attribute;
import com.guistraliote.attribute.AttributeRepository;
import com.guistraliote.attribute.exceptions.AttributeNotFoundException;
import com.guistraliote.category.Category;
import com.guistraliote.category.CategoryRepository;
import com.guistraliote.category.exceptions.CategoryNotFoundException;
import com.guistraliote.product.exceptions.ProductNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.modelmapper.ModelMapper;

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

    @Inject
    ModelMapper mapper;

    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        Product product = mapper.map(productDTO, Product.class);

        if (Objects.nonNull(productDTO.getCategoryId())) {
            Category category = categoryRepository.findById(productDTO.getCategoryId());
            if (Objects.isNull(category)) {
                throw new CategoryNotFoundException("Category not found for ID: " + productDTO.getCategoryId());
            }
            product.setCategory(category);
        }

        if (checkNullableAttribute(productDTO)) {
            Set<Attribute> attributes = new HashSet<>();
            for (Long attributeId : productDTO.getAttributeIds()) {
                Attribute attribute = attributeRepository.findById(attributeId);
                if (Objects.isNull(attribute)) {
                    throw new AttributeNotFoundException("Attribute not found for ID: " + attributeId);
                }
                attributes.add(attribute);
            }
            product.setAttributes(attributes);
        }
        productRepository.persist(product);

        return mapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> findAllPaged(int index, int pageSize) {
        List<Product> products = productRepository.findPaged(index, pageSize);

        return products.stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO findBySku(String sku) {
        Product product = productRepository.findBySku(sku);
        if (Objects.nonNull(product)) {
            return mapper.map(product, ProductDTO.class);
        }
        throw new ProductNotFoundException("Product sku " + sku + " not found.");
    }

    public ProductDTO findByTitle(String title) {
        Product product = productRepository.findByTitle(title);
        if (Objects.nonNull(title)) {
            return mapper.map(product, ProductDTO.class);
        }
        throw new ProductNotFoundException("Product " + title + " not found.");
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId());

        if (Objects.isNull(product)) {
            throw new ProductNotFoundException("Product not found for ID: " + productDTO.getId());
        }

        product.setTitle(productDTO.getTitle());
        product.setSku(productDTO.getSku());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setBrand(productDTO.getBrand());
        product.setWeight(productDTO.getWeight());
        product.setUpdateDate(productDTO.getUpdateDate());

        productRepository.persist(product);

        return mapper.map(product, ProductDTO.class);
    }

    public Response delete(Long id) {
        if (productRepository.findByIdOptional(id).isEmpty()) {
            throw new ProductNotFoundException("Cannot find product ID: " + id);
        }
        productRepository.deleteById(id);

        return Response.noContent().build();
    }

    private Boolean checkNullableAttribute(ProductDTO dto) {
       return Objects.nonNull(dto.getAttributeIds()) && !dto.getAttributeIds().isEmpty();
    }
}
