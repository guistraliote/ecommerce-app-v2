package com.guistraliote.category;


import com.guistraliote.queue.Queues;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class CategoryProducer {

    @Inject
    @Channel(Queues.CREATE_CATEGORY_QUEUE)
    @Broadcast
    Emitter<CategoryDTO> createEmitter;

    @Inject
    @Channel(Queues.UPDATE_CATEGORY_QUEUE)
    @Broadcast
    Emitter<CategoryDTO> updateEmitter;

    @Inject
    @Channel(Queues.DELETE_CATEGORY_QUEUE)
    @Broadcast
    Emitter<Long> deleteEmitter;

    public void sendCreate(CategoryDTO categoryDTO) {
        createEmitter.send(categoryDTO);
    }

    public void sendUpdate(CategoryDTO categoryDTO) {
        updateEmitter.send(categoryDTO);
    }

    public void sendDelete(Long id) {
        deleteEmitter.send(id);
    }
}
