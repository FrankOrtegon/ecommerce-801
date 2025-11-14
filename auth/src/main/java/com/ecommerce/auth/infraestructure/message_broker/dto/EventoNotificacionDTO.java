package com.ecommerce.auth.infraestructure.message_broker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoNotificacionDTO {
    private String tipo;
    private String email;
    private String numeroTelefono;
    private String mensaje;
}
