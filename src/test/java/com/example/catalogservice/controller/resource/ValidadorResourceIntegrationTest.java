package com.example.catalogservice.controller.resource;

import com.example.catalogservice.CatalogServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CatalogServiceApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ValidadorResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validateNoFallback() throws Exception {
        mockMvc.perform(get("/api/validate")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(is(notNullValue())));
    }

    @Test
    public void validateFallback() throws Exception {
        mockMvc.perform(get("/api/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-No-Fail", "Si"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("fallback")));
    }

}