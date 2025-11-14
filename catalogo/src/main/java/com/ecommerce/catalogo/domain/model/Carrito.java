package com.ecommerce.catalogo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {

    private Long id;
    private Long usuarioId;
    private List<ItemCarrito> items;
    private Double precioTotal;
}
