package com.guistraliote.category;

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
public class CategoryProducer {

    private static final Logger LOGGER = Logger.getLogger(CategoryProducer.class);

    @Inject
    @Channel(Topics.CREATE_CATEGORY_TOPIC)
    Emitter<Record<String, CategoryDTO>> createEmitter;

    @Inject
    @Channel(Topics.UPDATE_CATEGORY_TOPIC)
    Emitter<Record<String, CategoryDTO>> updateEmitter;

    @Inject
    @Channel(Topics.DELETE_CATEGORY_TOPIC)
    Emitter<Record<String, Long>> deleteEmitter;

    public void sendCreate(CategoryDTO categoryDTO) {
        createEmitter.send(Record.of(UUID.randomUUID().toString(), categoryDTO));
        LOGGER.infov("{0} - Sent CREATE message for category: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                categoryDTO.getName());
    }

    public void sendUpdate(CategoryDTO categoryDTO) {
        updateEmitter.send(Record.of(categoryDTO.getId().toString(), categoryDTO));
        LOGGER.infov("{0} - Sent UPDATE message with ID: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                categoryDTO.getId());
    }

    public void sendDelete(Long id) {
        deleteEmitter.send(Record.of(id.toString(), id));
        LOGGER.infov("{0} - Sent DELETE message with ID: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                id);
    }
}