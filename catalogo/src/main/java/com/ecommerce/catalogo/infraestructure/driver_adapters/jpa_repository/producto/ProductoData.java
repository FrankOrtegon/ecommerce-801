package com.ecommerce.catalogo.infraestructure.driver_adapters.jpa_repository.producto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String imagenUrl;
}
