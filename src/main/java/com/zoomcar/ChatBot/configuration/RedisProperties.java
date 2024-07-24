package com.zoomcar.ChatBot.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "redis")
@Data
@NoArgsConstructor
public class RedisProperties {
    private String host;
    private int database;
}
