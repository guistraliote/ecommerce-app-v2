package com.guistraliote.product;

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
public class ProductConsumer {

    private static final Logger LOGGER = Logger.getLogger(ProductConsumer.class);

    @Inject
    ProductService productService;

    @Incoming(Topics.CREATE_PRODUCT_TOPIC)
    @Blocking
    public void receiveCreate(Record<String, ProductDTO> record) {
        ProductDTO productDTO = record.value();
        productService.create(productDTO);
        LOGGER.infov("{0} - Received and processed CREATE message for product: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                productDTO.getTitle());
    }

    @Incoming(Topics.UPDATE_PRODUCT_TOPIC)
    @Blocking
    public void receiveUpdate(Record<String, ProductDTO> record) {
        ProductDTO productDTO = record.value();
        productService.updateProduct(productDTO.getId(), productDTO);
        LOGGER.infov("{0} - Received and processed UPDATE message for product ID: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                productDTO.getId());
    }

    @Incoming(Topics.DELETE_PRODUCT_TOPIC)
    @Blocking
    public void receiveDelete(Record<String, Long> record) {
        Long id = record.value();
        productService.delete(id);
        LOGGER.infov("{0} - Received and processed DELETE message for product ID: {1}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                id);
    }
}
