package com.zoomcar.ChatBot.controller.growthbook;

import com.zoomcar.ChatBot.service.QdrantService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/growthbook")
public class GrowthBookRefreshController {

    @Autowired
    private RedissonClient redissonClient;


    @GetMapping("/get")
    public String model(@RequestParam(value = "message", defaultValue = "Hello") String key) {
        return null;
    }


}
