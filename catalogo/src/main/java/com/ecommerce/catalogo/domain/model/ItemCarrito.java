package com.ecommerce.catalogo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrito {
    private Long id;
    private Long carritoId;
    private Long productoId;
    private String nombreProducto;
    private Double precioUnitario;
    private Integer cantidad;
    private Double subtotal;

}
