package io.hexlet.spring.dto;

import io.hexlet.spring.model.Page;
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

    public PageDTO(Page page) {
        id = page.getId();
        slug = page.getSlug();
        name = page.getName();
        body = page.getBody();
    }
}
