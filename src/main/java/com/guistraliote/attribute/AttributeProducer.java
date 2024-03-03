package com.guistraliote.attribute;

import com.guistraliote.topics.Topics;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@ApplicationScoped
public class AttributeProducer {

    private static final Logger LOGGER = Logger.getLogger(AttributeProducer.class);

    @Inject
    @Channel(Topics.CREATE_ATTRIBUTE_TOPIC)
    Emitter<Record<String, AttributeDTO>> createEmitter;

    @Inject
    @Channel(Topics.UPDATE_ATTRIBUTE_TOPIC)
    Emitter<Record<String, AttributeDTO>> updateEmitter;

    @Inject
    @Channel(Topics.DELETE_ATTRIBUTE_TOPIC)
    Emitter<Record<String, Long>> deleteEmitter;

    public void sendCreate(AttributeDTO attributeDTO) {
        createEmitter.send(Record.of(UUID.randomUUID().toString(), attributeDTO));
        LOGGER.infov("{0} - Sent CREATE message for attribute: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                attributeDTO.getAttributeName());
    }

    public void sendUpdate(AttributeDTO attributeDTO) {
        updateEmitter.send(Record.of(attributeDTO.getId().toString(), attributeDTO));
        LOGGER.infov("{0} - Sent UPDATE message with id: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                attributeDTO.getId());
    }

   public void sendDelete(Long id) {
        deleteEmitter.send(Record.of(id.toString(), id));
        LOGGER.infov("{0} - Sent DELETE message with id: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                id);
   }
}