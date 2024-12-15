package io.hexlet.spring.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.hexlet.spring.model.Page;
import io.hexlet.spring.repository.PageRepository;

import net.datafaker.Faker;

import org.instancio.Instancio;
import org.instancio.Select;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
public class PagesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PageRepository pageRepository;

    @AfterEach
    public void destroy() {
        pageRepository.deleteAll();
    }

    @Test
    public void testIndex() throws Exception {
        MvcResult result = mockMvc.perform(get("/pages"))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }

    @Test
    public void testShow() throws Exception {
        Page page = createPage();
        pageRepository.save(page);

        MvcResult result = mockMvc.perform(get("/pages/{id}", page.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        assertThatJson(body).isObject().containsEntry("id", BigDecimal.valueOf(page.getId()));
    }

    @Test
    public void testPost() throws Exception {
        Page page = createPage();

        mockMvc.perform(post("/pages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(page)))
                .andExpect(status().isCreated());

        Page task = pageRepository.findBySlug(page.getSlug()).get();

        assertEquals(task.getSlug(), page.getSlug());
        assertEquals(task.getName(), page.getName());
        assertEquals(task.getBody(), page.getBody());
    }

    @Test
    public void testPut() throws Exception {

    }

    private Page createPage() {
        return Instancio.of(Page.class)
                .ignore(Select.field(Page::getId))
                .create();
    }
}
