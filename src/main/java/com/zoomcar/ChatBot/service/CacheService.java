package com.zoomcar.ChatBot.service;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private RedissonClient redissonClient;

    public String get(String key) {
        RBucket<Object> rBucket = redissonClient.getBucket(key, JsonJacksonCodec.INSTANCE);
        String value = String.valueOf(rBucket.get());
        return value;
    }

    public void put(String key, String val) {
        RBucket<Object> rBucket = redissonClient.getBucket(key, JsonJacksonCodec.INSTANCE);
        rBucket.set(String.valueOf(val));
    }

}
