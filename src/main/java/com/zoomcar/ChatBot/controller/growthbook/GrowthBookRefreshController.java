package com.zoomcar.ChatBot.controller.growthbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zoomcar.ChatBot.service.CacheService;
import com.zoomcar.ChatBot.service.GrowthBookManager;
import com.zoomcar.ChatBot.service.QdrantService;
import org.redisson.api.RedissonClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.POST;

@RestController
@RequestMapping("/growthbook")
public class GrowthBookRefreshController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private GrowthBookManager growthBookManager;

    @GetMapping("/get")
    public String model(@RequestParam(value = "message", defaultValue = "Hello") String key) {
        var result = cacheService.get("hello");
        return result;
    }

    @PostMapping("/repopulate")
    public void populate () throws Exception {
        String features = growthBookManager.fetchFeatures();
        cacheService.put(features,);
        if(features != null) {
        }

    }





}
