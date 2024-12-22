package io.hexlet.spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PageDTO {

    @NotNull
    private Long id;

    @NotNull
    private String slug;

    @NotNull
    private String name;

    private String body;
}
