package com.ecommerce.notification.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsConfig {

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        "accessKeyId",
                                        "secretAccessKey"
                                )
                        )
                )                .build();
    }

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        "accessKeyId",
                                        "secretAccessKey"
                                )
                        )
                )                .build();
    }

    @Bean
    public SesClient sesClient() {
        return SesClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        "accessKeyId",
                                        "secretAccessKey"
                                )
                        )
                )                .build();
    }
}
