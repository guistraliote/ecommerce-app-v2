package com.guistraliote.attribute;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class AttributeDTO implements Serializable {
    private Long id;
    private String attributeName;
    private String attributeValue;
    private Boolean isActive;
}
