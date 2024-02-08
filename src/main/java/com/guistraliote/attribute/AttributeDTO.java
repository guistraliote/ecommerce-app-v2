package com.guistraliote.attribute;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeDTO {
    private Long id;
    private String attributeName;
    private String attributeValue;
    private Boolean isActive;
}
