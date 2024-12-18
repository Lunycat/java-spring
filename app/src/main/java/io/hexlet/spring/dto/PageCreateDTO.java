package io.hexlet.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageCreateDTO {

    private String slug;
    private String name;
    private String body;
}
