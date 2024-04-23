package com.guistraliote.attribute;


import com.guistraliote.attribute.exceptions.AttributeNotFoundException;
import com.guistraliote.attribute.exceptions.InvalidAttributeNameException;
import com.guistraliote.attribute.Attribute;
import com.guistraliote.attribute.AttributeDTO;
import com.guistraliote.attribute.exceptions.AttributeNotFoundException;
import com.guistraliote.attribute.Attribute;
import com.guistraliote.attribute.AttributeDTO;
import com.guistraliote.attribute.exceptions.InvalidAttributeNameException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class AttributeService {

    @Inject
    AttributeRepository attributeRepository;

    @Inject
    ModelMapper mapper;

    public List<AttributeDTO> findAllPaged(int pageIndex, int pageSize) {
        List<Attribute> attributes = attributeRepository.findPaged(pageIndex, pageSize);

        return attributes.stream()
                .map(attribute -> mapper.map(attribute, AttributeDTO.class))
                .collect(Collectors.toList());
    }

    public AttributeDTO findById(Long id) {
        Optional<Attribute> attribute = Optional.ofNullable(attributeRepository.findByIdOptional(id)
                .orElseThrow(() -> new AttributeNotFoundException("Attribute not found for ID: " + id)));

        return mapper.map(attribute, AttributeDTO.class);
    }

    public AttributeDTO create(AttributeDTO attributeDTO) {
        if (checkAttributeName(attributeDTO)) {
            throw new InvalidAttributeNameException("Attribute name cannot be null or empty.");
        }
        Attribute attribute = mapper.map(attributeDTO, Attribute.class);
        attributeRepository.persist(attribute);

        return mapper.map(attribute, AttributeDTO.class);
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
