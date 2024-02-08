package com.guistraliote.attribute;


import com.guistraliote.attribute.exceptions.AttributeNotFoundException;
import com.guistraliote.attribute.exceptions.InvalidAttributeNameException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class AttributeService {

    @Inject
    AttributeRepository attributeRepository;

    public List<AttributeDTO> findAllPaged(int pageIndex, int pageSize) {
        List<Attribute> attributes = attributeRepository.findPaged(pageIndex, pageSize);

        return attributes.stream()
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

        if (Objects.isNull(attribute)) {
            throw new AttributeNotFoundException("Attribute not found for ID: " + id);
        }

        return AttributeDTO.builder()
                .id(attribute.getId())
                .attributeName(attribute.getAttributeName())
                .attributeValue(attribute.getAttributeValue())
                .isActive(attribute.getIsActive())
                .build();
    }

    public AttributeDTO create(AttributeDTO attributeDTO) {
        if (checkAttributeName(attributeDTO)) {
            throw new InvalidAttributeNameException("Attribute name cannot be null or empty.");
        }

        Attribute attribute = Attribute.builder()
                .attributeName(attributeDTO.getAttributeName())
                .attributeValue(attributeDTO.getAttributeValue())
                .isActive(attributeDTO.getIsActive())
                .build();

        attributeRepository.persist(attribute);

        return attributeDTO;
    }

    public AttributeDTO update(Long id, AttributeDTO attributeDTO) {
        if (Objects.isNull(id)) {
            throw new AttributeNotFoundException("Attribute ID cannot be null");
        }

        Attribute attribute = attributeRepository.findById(id);

        if (Objects.isNull(attribute)) {
            throw new AttributeNotFoundException("Attribute not found for ID: " + id);
        }

        attribute.setAttributeName(attributeDTO.getAttributeName());
        attribute.setAttributeValue(attributeDTO.getAttributeValue());
        attribute.setIsActive(attributeDTO.getIsActive());

        attributeRepository.persist(attribute);

        return attributeDTO;
    }

    public void delete(Long id) {
        if (attributeRepository.findByIdOptional(id).isPresent()) {
            attributeRepository.deleteById(id);
        } else {
            throw new AttributeNotFoundException("Attribute not found for ID: " + id);
        }
    }

    private Boolean checkAttributeName(AttributeDTO dto) {
        return Objects.isNull(dto.getAttributeName()) || dto.getAttributeName().trim().isEmpty();
    }
}
