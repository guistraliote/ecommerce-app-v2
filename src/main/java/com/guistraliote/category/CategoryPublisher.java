package com.guistraliote.category;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CategoryPublisher {

    @Inject
    CategoryProducer categoryProducer;

    public void sendPostCategoryToQueue(CategoryDTO categoryDTO) {
        categoryProducer.sendCreate(categoryDTO);
    }

    public void sendPutCategoryToQueue(CategoryDTO categoryDTO) {
        categoryProducer.sendUpdate(categoryDTO);
    }

    public void sendDeleteCategoryToQueue(Long id) {
        categoryProducer.sendDelete(id);
    }
}
