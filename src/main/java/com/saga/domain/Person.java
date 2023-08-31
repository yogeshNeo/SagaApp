package com.ai.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "person")
public class Person {

    @Id
    private Long id;

    private String name;

    private int age;
}
