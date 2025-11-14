package com.ecommerce.notification.infraestructure.sqs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventoNotificacionDTO {
    private String tipo;
    private String email;
    private String numeroTelefono;
    private String mensaje;
}
