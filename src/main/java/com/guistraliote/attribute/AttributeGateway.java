package com.guistraliote.attribute;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AttributeGateway {

    @Inject
    AttributeProducer attributeProducer;

    public void sendPostAttributeToQueue(AttributeDTO attributeDTO) {
        attributeProducer.sendCreate(attributeDTO);
    }

    public void sendPutAttributeToQueue(AttributeDTO attributeDTO) {
        attributeProducer.sendUpdate(attributeDTO);
    }

    public void sendDeleteAttributeToQueue(Long id) {
        attributeProducer.sendDelete(id);
    }
}
