package com.guistraliote.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String title;
    private String sku;
    private String description;
    private Double price;
    private String image;
    private Integer stockQuantity;
    private String brand;
    private Double weight;
    LocalDateTime updateDate = LocalDateTime.now();
    private Set<Long> attributeIds;
    private Long categoryId;
}
