package com.zoomcar.ChatBot.beans;


import com.zoomcar.ChatBot.configuration.MinioProperties;
import com.zoomcar.ChatBot.configuration.QdrantProperties;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioClientConfig {

    @Autowired
    MinioProperties minioProperties;




}
