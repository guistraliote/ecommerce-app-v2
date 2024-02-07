package com.guistraliote.attribute;

import org.apache.kafka.common.protocol.types.Field;

public record AttributeDTO(
        String attributeName,
        String attributeValue,
        Boolean isActive
) {
}
