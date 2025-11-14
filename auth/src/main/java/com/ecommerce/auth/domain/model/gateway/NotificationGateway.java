package com.ecommerce.auth.domain.model.gateway;

import com.ecommerce.auth.domain.model.Notificacion;

public interface NotificationGateway {

    void enviarMensaje(Notificacion mensajeJson);
}
