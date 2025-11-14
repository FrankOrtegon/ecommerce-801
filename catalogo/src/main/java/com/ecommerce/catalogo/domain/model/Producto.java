package com.ecommerce.catalogo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String imagenUrl;
}
