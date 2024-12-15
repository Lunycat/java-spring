package io.hexlet.spring.controller.api;

import io.hexlet.spring.model.Page;
import io.hexlet.spring.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PagesController {

    @Autowired
    private PageRepository pageRepository;

    @GetMapping("/pages")
    public List<Page> index(@RequestParam(defaultValue = "10") Integer limit) {
        return pageRepository.findAll().stream().limit(limit).toList();
    }

    @PostMapping("/pages")
    @ResponseStatus(HttpStatus.CREATED)
    public Page create(@RequestBody Page page) {
        pageRepository.save(page);
        return page;
    }

    @GetMapping("pages/{id}")
    public Optional<Page> show(@PathVariable Long id) {
        return pageRepository.findById(id);
    }

    @PutMapping
    public Page update(@PathVariable Long id, @RequestBody Page data) {
        Optional<Page> maybePage = pageRepository.findById(id);

        if (maybePage.isPresent()) {
            Page page = maybePage.get();
            page.setSlug(data.getSlug());
            page.setName(data.getName());
            page.setBody(data.getBody());
        }

        return data;
    }

    @DeleteMapping("/pages/{id}")
    public void destroy(@PathVariable Long id) {
        pageRepository.deleteById(id);
    }
}