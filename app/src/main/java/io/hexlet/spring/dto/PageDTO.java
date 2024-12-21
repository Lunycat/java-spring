package io.hexlet.spring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PageDTO {

    private Long id;
    private String slug;
    private String name;
    private String body;
}
