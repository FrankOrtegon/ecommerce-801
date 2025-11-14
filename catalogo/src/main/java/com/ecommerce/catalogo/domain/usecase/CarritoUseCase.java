package com.ecommerce.catalogo.domain.usecase;

import com.ecommerce.catalogo.domain.exception.CarritoNoFoundException;
import com.ecommerce.catalogo.domain.exception.UsuarioNoEncontradoException;
import com.ecommerce.catalogo.domain.model.Carrito;
import com.ecommerce.catalogo.domain.model.ItemCarrito;
import com.ecommerce.catalogo.domain.model.Producto;
import com.ecommerce.catalogo.domain.model.gateway.CarritoGateway;
import com.ecommerce.catalogo.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CarritoUseCase {

    private final CarritoGateway carritoGateway;
    private final ProductoUseCase productoUseCase;
    private final UsuarioGateway usuarioGateway;


    public Carrito agregarProductoAlCarrito(Long usuarioId, ItemCarrito itemCarrito) {

        if (!usuarioGateway.usuarioExiste(usuarioId)) {
            throw new  UsuarioNoEncontradoException("Usuario no encontrado con ID: " + usuarioId);
        }

        Producto producto = productoUseCase.obtenerProducto(itemCarrito.getProductoId());

        itemCarrito.setNombreProducto(producto.getNombre());
        itemCarrito.setPrecioUnitario(producto.getPrecio());
        itemCarrito.setSubtotal(producto.getPrecio() * itemCarrito.getCantidad());

        Carrito carrito = carritoGateway.buscarPorUsuarioId(usuarioId);


        if (carrito == null) {
            carrito = new Carrito();
            carrito.setUsuarioId(usuarioId);
            carrito.setItems(new ArrayList<>());
        }

        Optional<ItemCarrito> existente = carrito.getItems()
                .stream()
                .filter(itemCarrito1 -> itemCarrito1.getProductoId().equals(itemCarrito.getProductoId()))
                .findFirst();

        if (existente.isPresent()) {
            ItemCarrito itemExistente = existente.get();
            itemExistente.setCantidad(itemExistente.getCantidad() + itemCarrito.getCantidad());
            itemExistente.setSubtotal(itemExistente.getPrecioUnitario() * itemExistente.getCantidad());
        } else {
            carrito.getItems().add(itemCarrito);
        }

        double total = carrito.getItems().stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();

        carrito.setPrecioTotal(total);

        return carritoGateway.guardar(carrito);
    }

    public Carrito verCarrito(Long usuarioId) {
        return carritoGateway.buscarPorUsuarioId(usuarioId);
    }


    public Carrito eliminarProductoDelCarrito(Long usuarioId, Long productoId) {
        System.out.println("Eliminando producto ID: " + productoId + " del carrito del usuario ID: " + usuarioId);
        Carrito carrito = carritoGateway.buscarPorUsuarioId(usuarioId);

        if (carrito == null) {
            throw new CarritoNoFoundException("Carrito no encontrado para el usuario ID: " + usuarioId);
        }

        List<ItemCarrito> itemsFiltrados = carrito.getItems()
                .stream()
                .filter(item -> !item.getProductoId().equals(productoId))
                .collect(Collectors.toList());

        carrito.setItems(itemsFiltrados);

        double total = itemsFiltrados.stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();

        carrito.setPrecioTotal(total);

        return carritoGateway.guardar(carrito);
    }

    public String realizarVenta(Long usuarioId) {
        Carrito carrito = carritoGateway.buscarPorUsuarioId(usuarioId);

        if (carrito == null || carrito.getItems().isEmpty()) {
            throw new RuntimeException("El carrito está vacío o no existe para el usuario.");
        }

        for (ItemCarrito item : carrito.getItems()) {
            Producto producto = productoUseCase.obtenerProducto(item.getProductoId());

            if (producto == null) {
                throw new RuntimeException("Producto con ID " + item.getProductoId() + " no encontrado.");
            }

            if (producto.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            // Descontar stock
            producto.setStock(producto.getStock() - item.getCantidad());
            productoUseCase.guardarProducto(producto);
        }

        // Eliminar el carrito del usuario
        carritoGateway.eliminarCarritoByUsuarioId(usuarioId);

        return "La compra fue realizada exitosamente";
    }
}
