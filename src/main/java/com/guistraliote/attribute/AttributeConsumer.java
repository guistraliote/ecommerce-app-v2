package com.guistraliote.attribute;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class AttributeConsumer {

    @Incoming("attributes-in")
    public void receiveAttribute(AttributeDTO attributeDTO) {
        // processar mensagem do kafka
    }
}
