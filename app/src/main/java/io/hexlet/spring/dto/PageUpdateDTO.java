package io.hexlet.spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageUpdateDTO {

    @NotNull
    private String name;

    private String body;
}
