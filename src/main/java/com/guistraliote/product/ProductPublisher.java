package com.guistraliote.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductPublisher {

    @Inject
    ProductProducer productProducer;

    public void sendPostProductToQueue(ProductDTO productDTO) {
        productProducer.sendCreate(productDTO);
    }

    public void sendPutProductToQueue(ProductDTO productDTO) {
        productProducer.sendUpdate(productDTO);
    }

    public void sendDeleteProductToQueue(Long id) {
        productProducer.sendDelete(id);
    }
}
