package com.guistraliote.category;

import com.guistraliote.topics.Topics;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class CategoryProducer {

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
        createEmitter.send(Record.of(categoryDTO.toString(), categoryDTO));
    }

    public void sendUpdate(CategoryDTO categoryDTO) {
        updateEmitter.send(Record.of(categoryDTO.getId().toString(), categoryDTO));
    }

    public void sendDelete(Long id) {
        deleteEmitter.send(Record.of(id.toString(), id));
    }
}
