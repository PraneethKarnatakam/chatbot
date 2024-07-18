package com.zoomcar.ChatBot.beans;


import com.zoomcar.ChatBot.configuration.QdrantProperties;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QdrantClientConfig {

    @Autowired
    QdrantProperties qdrantProperties;

    @Bean
    public QdrantClient qdrantClient() {
        QdrantClient client =
                new QdrantClient(
                        QdrantGrpcClient.newBuilder(qdrantProperties.getUrl(), qdrantProperties.getPort() , false)
                                .build());

        return client;
    }



}
