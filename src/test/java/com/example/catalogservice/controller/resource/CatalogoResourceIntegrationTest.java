package com.example.catalogservice.controller.resource;

import com.example.catalogservice.CatalogServiceApplication;
import com.example.catalogservice.dto.CatalogoDto;
import com.example.catalogservice.entity.Catalogo;
import com.example.catalogservice.repository.CatalogoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CatalogServiceApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CatalogoResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @After
    public void reset() {
        catalogoRepository.deleteAll();
    }

    @Before
    public void setUp() {
        createCatalogo("Test1", "Test1");
        createCatalogo("Test2", "Test2");
        createCatalogo("Test3", "Test3");
        createCatalogo("Test4", "Test4");
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/api/catalogo").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));
    }

    @Test
    public void findOneById() throws Exception {
        mockMvc.perform(get("/api/catalogo/-11").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        Optional<Long> id = catalogoRepository.findAll().stream().findFirst().map(catalogo -> catalogo.getId());

        id.ifPresent(i -> {
            try {
                mockMvc.perform(get("/api/catalogo/" + i).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
            } catch (Exception ignored) {
            }
        });

    }

    @Test
    public void save() throws Exception {
        CatalogoDto catalogoDto = new CatalogoDto();
        catalogoDto.setNombre("Prueba post");
        catalogoDto.setDescripcion("Prueba post");

        mockMvc.perform(post("/api/catalogo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(catalogoDto)))
                .andExpect(status().isCreated());

        List<Catalogo> resultado = catalogoRepository.findByNombre("Prueba post");

        assertThat(resultado, hasSize(greaterThanOrEqualTo(1)));
        assertThat(resultado.get(0), hasProperty("nombre", equalTo("Prueba post")));
        assertThat(resultado.get(0).getId(), is(notNullValue()));
    }

    private void createCatalogo(String nombre, String descripion) {
        Catalogo catalogo = Catalogo.builder().nombre(nombre).descripcion(descripion).build();
        catalogoRepository.save(catalogo);
    }

}