package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogoDto;
import com.example.catalogservice.entity.Catalogo;
import com.example.catalogservice.repository.CatalogoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void save(CatalogoDto catalogoDto) {
        catalogoRepository.save(convertToEntity(catalogoDto));
    }

    @Transactional(readOnly = true)
    public Optional<CatalogoDto> findById(Long id) {
        return catalogoRepository.findById(id).map(this::converToDto);
    }

    @Transactional(readOnly = true)
    public List<CatalogoDto> findAll() {
        return catalogoRepository.findAll().stream().map(this::converToDto).collect(Collectors.toList());
    }

    private CatalogoDto converToDto(Catalogo catalogo) {
        return modelMapper.map(catalogo, CatalogoDto.class);
    }

    private Catalogo convertToEntity(CatalogoDto catalogoDto) {
        Catalogo catalogo = modelMapper.map(catalogoDto, Catalogo.class);
        if (catalogoDto.getId() != null) {
            Optional<Catalogo> oldCatalogo = catalogoRepository.findById(catalogoDto.getId());
            oldCatalogo.ifPresent(value -> catalogo.setCreatedDate(value.getCreatedDate()));
        }
        return catalogo;
    }

}
