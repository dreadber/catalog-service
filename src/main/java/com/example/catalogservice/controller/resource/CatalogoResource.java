package com.example.catalogservice.controller.resource;

import com.example.catalogservice.dto.CatalogoDto;
import com.example.catalogservice.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoResource {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping
    public List<CatalogoDto> findAll() {
        return catalogoService.findAll();
    }

    @GetMapping("/{id}")
    public CatalogoDto findOneById(@PathVariable("id") Long id) {
        Optional<CatalogoDto> catalogoDto = catalogoService.findById(id);

        if (catalogoDto.isPresent()) {
            return catalogoDto.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCatalogo(@RequestBody CatalogoDto catalogoDto) {
        catalogoService.save(catalogoDto);
    }

}