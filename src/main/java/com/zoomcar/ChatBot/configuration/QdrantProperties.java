package com.zoomcar.ChatBot.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "qdrant")
@Data
public class QdrantProperties {
    private String url;
    private int port;
}
