package com.guistraliote.product;

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
public class ProductProducer {
    private static final Logger LOGGER = Logger.getLogger(ProductProducer.class);

    @Inject
    @Channel(Topics.CREATE_PRODUCT_TOPIC)
    Emitter<Record<String, ProductDTO>> createEmitter;

    @Inject
    @Channel(Topics.UPDATE_PRODUCT_TOPIC)
    Emitter<Record<String, ProductDTO>> updateEmitter;

    @Inject
    @Channel(Topics.DELETE_PRODUCT_TOPIC)
    Emitter<Record<String, Long>> deleteEmitter;

    public void sendCreate(ProductDTO productDTO) {
        createEmitter.send(Record.of(UUID.randomUUID().toString(), productDTO));
        LOGGER.infov("{0} - Sent CREATE message for product: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                productDTO.getTitle());
    }

    public void sendUpdate(ProductDTO productDTO) {
        updateEmitter.send(Record.of(productDTO.getId().toString(), productDTO));
        LOGGER.infov("{0} - Sent UPDATE message with id: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                productDTO.getId());
    }

    public void sendDelete(Long id) {
        deleteEmitter.send(Record.of(id.toString(), id));
        LOGGER.infov("{0} - Sent DELETE message with id: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                id);
    }
}
