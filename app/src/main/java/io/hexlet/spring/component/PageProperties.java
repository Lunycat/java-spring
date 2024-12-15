package io.hexlet.spring.component;

import io.hexlet.spring.model.Page;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "pages")
@Getter
@Setter
public class PageProperties {
    private List<Page> pageList;

}
