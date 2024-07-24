package com.zoomcar.ChatBot.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "growthbook")
@Data
@NoArgsConstructor
public class GrowthBookProperties {
    private String hostUrl;
    private String apiKey;
    private String projectKey;
    private int requestTimeout;
}
