package com.guistraliote.attribute;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ATTRIBUTE_NAME")
    private String attributeName;

    @Column(name = "VALUE")
    private String attributeValue;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
}