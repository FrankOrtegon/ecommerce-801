package com.ecommerce.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Notificacion {
    private String tipo;
    private String email;
    private String numeroTelefono;
    private String mensaje;
}
