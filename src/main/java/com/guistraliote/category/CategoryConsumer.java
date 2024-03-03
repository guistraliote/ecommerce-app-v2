package com.guistraliote.category;

import com.guistraliote.topics.Topics;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class CategoryConsumer {

    @Inject
    CategoryService categoryService;

    @Incoming(Topics.CREATE_CATEGORY_TOPIC)
    @Blocking
    public void receiveCreate(Record<String, CategoryDTO> record) {
        CategoryDTO categoryDTO = record.value();
        categoryService.create(categoryDTO);
    }

    @Incoming(Topics.UPDATE_CATEGORY_TOPIC)
    @Blocking
    public void receiveUpdate(Record<String, CategoryDTO> record) {
        CategoryDTO categoryDTO = record.value();
        categoryService.update(categoryDTO.getId(), categoryDTO);
    }

    @Incoming(Topics.DELETE_CATEGORY_TOPIC)
    @Blocking
    public void receiveDelete(Record<String, Long> record) {
        Long id = record.value();
        categoryService.delete(id);
    }
}
