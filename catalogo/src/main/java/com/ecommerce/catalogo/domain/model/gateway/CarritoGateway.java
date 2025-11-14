package com.ecommerce.catalogo.domain.model.gateway;

import com.ecommerce.catalogo.domain.model.Carrito;

public interface CarritoGateway {

    Carrito guardar(Carrito carrito);
    Carrito buscarPorUsuarioId(Long usuarioId);
    void eliminarCarritoByUsuarioId(Long usuarioId);
}
