package com.ecommerce.catalogo.infraestructure.driver_adapters.jpa_repository.itemcarrito;

import com.ecommerce.catalogo.infraestructure.driver_adapters.jpa_repository.carrito.CarritoData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_carrito")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarritoData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemCarrito;

    private Long productoId;
    private String nombreProducto;
    private Double precioUnitario;
    private Integer cantidad;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "id_carrito")
    private CarritoData carrito;
}
