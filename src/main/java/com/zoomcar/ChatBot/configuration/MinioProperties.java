package com.zoomcar.ChatBot.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "minio")
@Data
@NoArgsConstructor
public class MinioProperties {
    private String minioUrl;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
