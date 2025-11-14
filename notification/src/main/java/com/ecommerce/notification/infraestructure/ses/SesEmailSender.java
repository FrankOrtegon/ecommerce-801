package com.ecommerce.notification.infraestructure.ses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Component
@RequiredArgsConstructor
public class SesEmailSender {

    private final SesClient sesClient;


    private String from;

    public void enviarEmail(String to, String subject, String mensaje) {
        Destination destination = Destination.builder()
                .toAddresses(to)
                .build();

        Content subjContent = Content.builder().data(subject).build();
        Content bodyContent = Content.builder().data(mensaje).build();
        Body body = Body.builder().text(bodyContent).build();

        Message message = Message.builder()
                .subject(subjContent)
                .body(body)
                .build();

        SendEmailRequest request = SendEmailRequest.builder()
                .destination(destination)
                .message(message)
                .source(from)
                .build();

        sesClient.sendEmail(request);
    }
}
