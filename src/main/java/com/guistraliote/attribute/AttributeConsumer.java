package com.guistraliote.attribute;

import com.guistraliote.topics.Topics;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class AttributeConsumer {

    private static final Logger LOGGER = Logger.getLogger(AttributeConsumer.class);

    @Inject
    AttributeService attributeService;

    @Incoming(Topics.CREATE_ATTRIBUTE_TOPIC)
    @Blocking
    public void receiveCreate(Record<String, AttributeDTO> record) {
        AttributeDTO attributeDTO = record.value();
        attributeService.create(attributeDTO);
        LOGGER.infov("{0} - Received and processed CREATE message for attribute: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                attributeDTO.getAttributeName());
    }

    @Incoming(Topics.UPDATE_ATTRIBUTE_TOPIC)
    @Blocking
    public void receiveUpdate(Record<String, AttributeDTO> record) {
        AttributeDTO attributeDTO = record.value();
        attributeService.update(attributeDTO.getId(), attributeDTO);
        LOGGER.infov("{0} - Received and processed UPDATE message for attribute ID: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                attributeDTO.getId());
    }

    @Incoming(Topics.DELETE_ATTRIBUTE_TOPIC)
    @Blocking
    public void receiveDelete(Record<String, Long> record) {
        Long id = record.value();
        attributeService.delete(id);
        LOGGER.infov("{0} - Received and processed DELETE message for attribute ID: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                id);
    }
}
