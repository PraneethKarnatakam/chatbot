package com.zoomcar.ChatBot.beans;


import com.zoomcar.ChatBot.configuration.MinioProperties;
import com.zoomcar.ChatBot.configuration.QdrantProperties;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioClientConfig {

    @Autowired
    MinioProperties minioProperties;

    @Bean
    public MinioClient getMinioClient() {
        try {
            return MinioClient.builder()
                    .endpoint(minioProperties.getUrl())
                    .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create MinIO client", e);
        }
    }


}
