package com.ecommerce.catalogo.infraestructure.driver_adapters.jpa_repository.carrito;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarritoDataJpaRepository extends JpaRepository<CarritoData, Long> {

    Optional<CarritoData> findByUsuarioId(Long usuarioId);
    void deleteByUsuarioId(Long usuarioId);
}
