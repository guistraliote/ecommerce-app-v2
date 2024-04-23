package com.guistraliote.category;

import com.guistraliote.category.exceptions.CategoryNotFoundException;
import com.guistraliote.category.exceptions.InvalidCategoryNameException;
import com.guistraliote.product.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    ModelMapper mapper;

    public List<CategoryDTO> findAllPaged(int index, int pageSize) {
        List<Category> categories = categoryRepository.findPaged(index, pageSize);

        return categories.stream()
                .map(category -> mapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    public CategoryDTO findById(Long id) {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findByIdOptional(id)
                .orElseThrow(() -> new CategoryNotFoundException("Attribute not found for ID: " + id)));

        return mapper.map(category, CategoryDTO.class);
    }

    @Transactional
    public CategoryDTO create(CategoryDTO categoryDTO) {
        if (checkCategoryName(categoryDTO)) {
            throw new InvalidCategoryNameException("Category name cannot be null or empty.");
        }
        Category category = mapper.map(categoryDTO, Category.class);
        categoryRepository.persist(category);

        return mapper.map(category, CategoryDTO.class);
    }

    public CategoryDTO update (Long id, CategoryDTO categoryDTO) {
        if (Objects.isNull(id)) {
            throw new CategoryNotFoundException("Category ID cannot be null");
        }

        Category category = categoryRepository.findById(id);

        if (Objects.isNull(category)) {
            throw new CategoryNotFoundException("Category not found for ID: " + id);
        }

        category.setName(categoryDTO.getName());
        category.setIsActive(categoryDTO.getIsActive());
        category.setPath(categoryDTO.getPath());

        categoryRepository.persist(category);

        return categoryDTO;
    }

    public Long delete(Long id) {
        if (categoryRepository.findByIdOptional(id).isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new CategoryNotFoundException("Category not found for ID: " + id);
        }

        return null;
    }

    private Boolean checkCategoryName(CategoryDTO dto) {
        return Objects.isNull(dto.getName()) || dto.getName().trim().isEmpty();
    }
}
