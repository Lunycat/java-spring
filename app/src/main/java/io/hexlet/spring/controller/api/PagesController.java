package io.hexlet.spring.controller.api;

import io.hexlet.spring.dto.PageCreateDTO;
import io.hexlet.spring.dto.PageDTO;
import io.hexlet.spring.dto.PageUpdateDTO;
import io.hexlet.spring.exception.ResourceNotFoundException;
import io.hexlet.spring.mapper.PageMapper;
import io.hexlet.spring.model.Page;
import io.hexlet.spring.repository.PageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pages")
public class PagesController {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private PageMapper pageMapper;

    @GetMapping
    public List<PageDTO> index(@RequestParam(defaultValue = "10") Integer limit) {
        List<Page> pages = pageRepository.findAll();
        return pages.stream()
                .limit(limit)
                .map(p -> pageMapper.map(p))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PageDTO create(@RequestBody PageCreateDTO pageCreateDTO) {
        Page page = pageMapper.map(pageCreateDTO);
        pageRepository.save(page);
        return pageMapper.map(page);
    }

    @GetMapping("/{id}")
    public PageDTO show(@PathVariable Long id) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return pageMapper.map(page);
    }

    @PutMapping
    public PageDTO update(@PathVariable Long id, @RequestBody PageUpdateDTO data) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        pageMapper.update(data, page);
        return pageMapper.map(page);
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        pageRepository.deleteById(id);
    }
}
