package com.guistraliote.attribute;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AttributeService {

    @Inject
    AttributeRepository attributeRepository;

    public List<AttributeDTO> findAll() {
        return attributeRepository.listAll().stream()
                .map(attribute -> AttributeDTO.builder()
                        .id(attribute.getId())
                        .attributeName(attribute.getAttributeName())
                        .attributeValue(attribute.getAttributeValue())
                        .isActive(attribute.getIsActive())
                        .build())
                .collect(Collectors.toList());
    }

    public AttributeDTO findById(Long id) {
        Attribute attribute = attributeRepository.findById(id);
        return AttributeDTO.builder()
                .id(attribute.getId())
                .attributeName(attribute.getAttributeName())
                .attributeValue(attribute.getAttributeValue())
                .isActive(attribute.getIsActive())
                .build();
    }

    public AttributeDTO create(AttributeDTO attributeDTO) {
        Attribute attribute = Attribute.builder()
                .attributeName(attributeDTO.getAttributeName())
                .attributeValue(attributeDTO.getAttributeValue())
                .isActive(attributeDTO.getIsActive())
                .build();
        attributeRepository.persist(attribute);
        attributeDTO.setId(attribute.getId());
        return attributeDTO;
    }

    public AttributeDTO update(Long id, AttributeDTO attributeDTO) {
        Attribute attribute = attributeRepository.findById(id);
        if (attribute != null) {
            attribute.setAttributeName(attributeDTO.getAttributeName());
            attribute.setAttributeValue(attributeDTO.getAttributeValue());
            attribute.setIsActive(attributeDTO.getIsActive());
            attributeRepository.persist(attribute);
            return attributeDTO;
        }
        return null;
    }

    public void delete(Long id) {
        attributeRepository.deleteById(id);
    }
}
