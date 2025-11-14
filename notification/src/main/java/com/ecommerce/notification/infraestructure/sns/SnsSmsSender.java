package com.ecommerce.notification.infraestructure.sns;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Component
@RequiredArgsConstructor
public class SnsSmsSender {


    private final SnsClient snsClient;

    public void enviarSms(String mensaje, String numeroTelefono) {
        PublishRequest request = PublishRequest.builder()
                .message(mensaje)
                .phoneNumber("+57" + numeroTelefono)
                .build();

        snsClient.publish(request);
    }
}
