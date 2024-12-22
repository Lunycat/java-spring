package io.hexlet.spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageCreateDTO {

    @NotNull
    private String slug;

    @NotNull
    private String name;

    private String body;
}
