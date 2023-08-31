package com.ai.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String name;

    private int age;
}
