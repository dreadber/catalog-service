package com.example.catalogservice.service;

import com.example.catalogservice.repository.CatalogoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CatalogoServiceTest {

    @Autowired
    private CatalogoService catalogoService;

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Before
    public void reset() {
        catalogoRepository.deleteAll();
    }

    @Test
    public void testFinById() {
        assertFalse(catalogoService.findById(56L).isPresent());
    }

}