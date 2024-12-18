package io.hexlet.spring.controller.api;

import io.hexlet.spring.dto.PageDTO;
import io.hexlet.spring.exception.ResourceNotFoundException;
import io.hexlet.spring.model.Page;
import io.hexlet.spring.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pages")
public class PagesController {

    @Autowired
    private PageRepository pageRepository;

    @GetMapping
    public List<PageDTO> index(@RequestParam(defaultValue = "10") Integer limit) {
        List<Page> pages = pageRepository.findAll();
        return pages.stream()
                .limit(limit)
                .map(PageDTO::new)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Page create(@RequestBody Page page) {
        pageRepository.save(page);
        return page;
    }

    @GetMapping("/{id}")
    public PageDTO show(@PathVariable Long id) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return new PageDTO(page);
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

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        pageRepository.deleteById(id);
    }
}
