package com.ecommerce.catalogo.domain.usecase;

import com.ecommerce.catalogo.domain.model.Producto;
import com.ecommerce.catalogo.domain.model.gateway.ProductoGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductoUseCase {

    private final ProductoGateway productoGateway;


    public Producto guardarProducto(Producto producto) {
        return productoGateway.guardar(producto);
    }


    public Producto obtenerProducto(Long id) {
        try {
            return productoGateway.buscarPorId(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new Producto();
        }
    }


    public List<Producto> obtenerTodos() {
        return productoGateway.obtenerTodos();
    }


    public void eliminarProducto(Long id) {
        productoGateway.eliminar(id);
    }
}
