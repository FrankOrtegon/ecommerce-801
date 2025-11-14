package com.ecommerce.catalogo.infraestructure.entry_points;

import com.ecommerce.catalogo.domain.exception.CarritoNoFoundException;
import com.ecommerce.catalogo.domain.exception.UsuarioNoEncontradoException;
import com.ecommerce.catalogo.domain.model.Carrito;
import com.ecommerce.catalogo.domain.model.ItemCarrito;
import com.ecommerce.catalogo.domain.usecase.CarritoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecommerce/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoUseCase carritoUseCase;

    @PostMapping("/agregar")
    public ResponseEntity<Carrito> agregarItem(@RequestParam Long usuarioId, @RequestBody ItemCarrito item) {
        try {
            return ResponseEntity.ok(carritoUseCase.agregarProductoAlCarrito(usuarioId, item));
        }catch (UsuarioNoEncontradoException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Carrito> verCarrito(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(carritoUseCase.verCarrito(usuarioId));
    }

    @DeleteMapping("/eliminar/{productoId}")
    public ResponseEntity<Carrito> eliminarItemDelCarrito(
            @RequestParam Long usuarioId,
            @PathVariable Long productoId
    ) {
        try {
            Carrito carritoActualizado = carritoUseCase.eliminarProductoDelCarrito(usuarioId, productoId);
            return ResponseEntity.ok(carritoActualizado);
        }catch (CarritoNoFoundException errorMessage){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("venta/{usuarioId}")
    public ResponseEntity<String> realizarVenta(@PathVariable Long usuarioId) {
        try {
            String mensaje = carritoUseCase.realizarVenta(usuarioId);
            return ResponseEntity.ok(mensaje);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al realizar la venta: " + e.getMessage());
        }
    }
}
