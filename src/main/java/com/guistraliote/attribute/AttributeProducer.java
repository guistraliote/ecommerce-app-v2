package com.guistraliote.attribute;

import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class AttributeProducer {

    @Inject
    @Channel("attributes-out")
    Emitter<AttributeDTO> emitter;

    public void send(AttributeDTO attributeDTO) {
        emitter.send(KafkaRecord.of(attributeDTO.getId().toString(), attributeDTO));
    }
}
