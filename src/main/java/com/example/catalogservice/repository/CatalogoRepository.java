package com.example.catalogservice.repository;

import com.example.catalogservice.entity.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {

    List<Catalogo> findByNombre(String nombre);

}
