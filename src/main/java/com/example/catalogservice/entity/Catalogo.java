package com.example.catalogservice.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Catalogo extends AbstractEntity {

    private String nombre;

    private String descripcion;

}
