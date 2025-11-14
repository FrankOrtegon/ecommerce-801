package com.ecommerce.catalogo.infraestructure.driver_adapters.jpa_repository.carrito;

import com.ecommerce.catalogo.infraestructure.driver_adapters.jpa_repository.itemcarrito.ItemCarritoData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "carrito")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    private Long usuarioId;

    private Double precioTotal;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarritoData> items;
}
