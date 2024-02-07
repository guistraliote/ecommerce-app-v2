package com.guistraliote.attribute;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class AttributeService {

    @Inject
    AttributeRepository attributeRepository;

    @Transactional
    @NonNull
    public AttributeDTO create(AttributeDTO dto) {
        Attribute attribute = toEntity(dto);

        attributeRepository.persist(attribute);

        return toDTO(attribute);
    }

    public AttributeDTO getByName(AttributeDTO dto) {
        Attribute attribute = toEntity(dto);

        Attribute response = (Attribute) attributeRepository.getByName(attribute.getAttributeName());

        return toDTO(response);
    }

    @Transactional
    public List<AttributeDTO> getAllPaged(int page, int size) {
        return attributeRepository.findAll().page(Page.of(page, size))
                .list()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AttributeDTO getAllActive() {
        Attribute response = (Attribute) attributeRepository.getAllActive();

        return toDTO(response);
    }

    public AttributeDTO getById(Long id) {
        Optional<Attribute> obj = attributeRepository.findByIdOptional(id);

        return toDTO(obj.orElseThrow(NotFoundException::new));
    }

    @Transactional
    @NonNull
    public AttributeDTO update(Long id, AttributeDTO dto) {
        Attribute entity = attributeRepository.findById(id);

        Objects.requireNonNull(entity, "Entity not found");

        entity.setAttributeName(dto.attributeName());
        entity.setAttibuteValue(dto.attributeValue());

        attributeRepository.persist(entity);

        return toDTO(entity);
    }

    @Transactional
    public void delete(@NonNull Long id) {
        Attribute attribute = attributeRepository
                .findByIdOptional(id)
                .orElseThrow(NotFoundException::new);

        attributeRepository.delete(attribute);
    }

    public Attribute toEntity(AttributeDTO dto) {
        return new Attribute(
                dto.attributeName(),
                dto.attributeValue(),
                dto.isActive()
        );
    }

    public AttributeDTO toDTO(Attribute attribute) {
        return new AttributeDTO(
          attribute.getAttributeName(),
          attribute.getAttibuteValue(),
          attribute.getIsActive()
        );
    }
}
