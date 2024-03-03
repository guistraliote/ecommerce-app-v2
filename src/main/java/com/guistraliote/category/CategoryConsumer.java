package com.guistraliote.category;

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
public class CategoryConsumer {

    private static final Logger LOGGER = Logger.getLogger(CategoryConsumer.class);

    @Inject
    CategoryService categoryService;

    @Incoming(Topics.CREATE_CATEGORY_TOPIC)
    @Blocking
    public void receiveCreate(Record<String, CategoryDTO> record) {
        CategoryDTO categoryDTO = record.value();
        categoryService.create(categoryDTO);
        LOGGER.infov("{0} - Received and processed CREATE message for Category: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                categoryDTO.getName());
    }

    @Incoming(Topics.UPDATE_CATEGORY_TOPIC)
    @Blocking
    public void receiveUpdate(Record<String, CategoryDTO> record) {
        CategoryDTO categoryDTO = record.value();
        categoryService.update(categoryDTO.getId(), categoryDTO);
        LOGGER.infov("{0} - Received and processed UPDATE message for Category ID: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                categoryDTO.getId());
    }

    @Incoming(Topics.DELETE_CATEGORY_TOPIC)
    @Blocking
    public void receiveDelete(Record<String, Long> record) {
        Long id = record.value();
        categoryService.delete(id);
        LOGGER.infov("{0} - Received and processed DELETE message for Category ID: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                id);
    }
}
