package com.guistraliote.category;

import com.guistraliote.queue.Queues;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;


@ApplicationScoped
public class CategoryConsumer {

    @Inject
    CategoryService categoryService;

    @Incoming(Queues.CREATE_CATEGORY_QUEUE)
    public Uni<Void> receiveCreate(CategoryDTO categoryDTO) {
        return categoryService.createAsync(categoryDTO);
    }

    @Incoming(Queues.UPDATE_CATEGORY_QUEUE)
    public Uni<Void> receiveUpdate(CategoryDTO categoryDTO) {
        return categoryService.updateAsync(categoryDTO.getId(), categoryDTO);
    }

    @Incoming(Queues.DELETE_CATEGORY_QUEUE)
    public Uni<Void> receiveDelete(Long id) {
        return categoryService.deleteAsync(id);
    }
}