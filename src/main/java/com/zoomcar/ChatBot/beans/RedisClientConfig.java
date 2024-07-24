package com.zoomcar.ChatBot.beans;

import com.zoomcar.ChatBot.configuration.QdrantProperties;
import com.zoomcar.ChatBot.configuration.RedisProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisClientConfig {

    @Autowired
    RedisProperties redisProperties;

    private static String REDISSON_CLIENT = "Chatbot-Client";

    @Bean
    public RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(redisProperties.getHost());
        config.useSingleServer().setDatabase(redisProperties.getDatabase());
        config.useSingleServer().setClientName(REDISSON_CLIENT);
        config.useSingleServer().setConnectionMinimumIdleSize(8);
        config.useSingleServer().setConnectionPoolSize(64);
        config.useSingleServer().setRetryAttempts(0);
        config.setNettyThreads(32);
        return Redisson.create(config);
    }



}
