package com.guistraliote.attribute;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.NonNull;

import java.util.Optional;

@ApplicationScoped
public class AttributeService {

    @Inject
    AttributeRepository attributeRepository;

    @Transactional
    @NonNull
    public Attribute create(AttributeDTO dto) {
        Attribute attribute = toEntity(dto);
        attributeRepository.persist(attribute);

        return attribute;
    }

    public Attribute getByName(AttributeDTO dto) {
        Attribute attribute = toEntity(dto);

        return (Attribute) attributeRepository.getByName(attribute.getAttributeName());
    }

    public Attribute getAllPaged(int page, int size) {
        return (Attribute) attributeRepository.getAllPaged(page, size);
    }

    public Attribute getAllActive() {
        return (Attribute) attributeRepository.getAllActive();
    }

    public Attribute getById(Long id) {
        Optional<Attribute> obj = attributeRepository.findByIdOptional(id);

        return obj.orElseThrow(NotFoundException::new);
    }

    @Transactional
    @NonNull
    public AttributeDTO update(Long id, AttributeDTO dto) {
        Attribute entity = getById(id);

        toEntity(dto);

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
