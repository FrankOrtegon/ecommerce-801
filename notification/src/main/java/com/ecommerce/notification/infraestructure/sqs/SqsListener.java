package com.ecommerce.notification.infraestructure.sqs;

import com.ecommerce.notification.infraestructure.ses.SesEmailSender;
import com.ecommerce.notification.infraestructure.sns.SnsSmsSender;
import com.ecommerce.notification.infraestructure.sqs.dto.EventoNotificacionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class SqsListener {

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;
    private final SnsSmsSender smsSender;
    private final SesEmailSender emailSender;

    private final String QUEUE_URL = "URL-COLA";

    @PostConstruct
    public void escucharMensajes() {
        Executors.newSingleThreadExecutor().submit(() -> {
            while (true) {
                ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                        .queueUrl(QUEUE_URL)
                        .maxNumberOfMessages(5)
                        .waitTimeSeconds(10)
                        .build();

                List<Message> messages = sqsClient.receiveMessage(receiveRequest).messages();

                for (Message message : messages) {
                    try {
                        EventoNotificacionDTO evento = objectMapper.readValue(message.body(), EventoNotificacionDTO.class);

                        smsSender.enviarSms(evento.getMensaje(), evento.getNumeroTelefono());
                        emailSender.enviarEmail(evento.getEmail(), evento.getTipo(), evento.getMensaje());

                        sqsClient.deleteMessage(DeleteMessageRequest.builder()
                                .queueUrl(QUEUE_URL)
                                .receiptHandle(message.receiptHandle())
                                .build());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
