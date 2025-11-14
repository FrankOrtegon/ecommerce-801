package com.ecommerce.catalogo.infraestructure.entry_points;

import com.ecommerce.catalogo.domain.model.Producto;
import com.ecommerce.catalogo.domain.usecase.ProductoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ecommerce/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoUseCase useCase;


    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        return ResponseEntity.ok(useCase.guardarProducto(producto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.obtenerProducto(id));
    }


    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        return ResponseEntity.ok(useCase.obtenerTodos());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        useCase.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
