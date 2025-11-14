package com.ecommerce.catalogo.infraestructure.mapper;

import com.ecommerce.catalogo.domain.model.Producto;
import com.ecommerce.catalogo.infraestructure.driver_adapters.jpa_repository.producto.ProductoData;
import org.springframework.stereotype.Component;

@Component
public class MapperProducto {
    public Producto toProducto(ProductoData data) {
        return new Producto(
                data.getId(),
                data.getNombre(),
                data.getDescripcion(),
                data.getPrecio(),
                data.getStock(),
                data.getImagenUrl()
        );
    }


    public ProductoData toData(Producto producto) {
        return new ProductoData(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getImagenUrl()
        );
    }
}
