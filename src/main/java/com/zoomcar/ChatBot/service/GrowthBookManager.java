package com.zoomcar.ChatBot.service;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonElement;
import com.zoomcar.ChatBot.configuration.GrowthBookProperties;
import growthbook.sdk.java.GBContext;
import growthbook.sdk.java.GrowthBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class GrowthBookManager {

    @Autowired
    private GrowthBookProperties growthBookProperties;

    @Autowired
    private RestTemplate restTemplate;

    private final String fetchFeaturesPath = "/api/features/";

    public String fetchFeatures()  throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("project", growthBookProperties.getProjectKey());
        String response = null;
        try {
            response = apiCallFeatures(growthBookProperties.getHostUrl(), fetchFeaturesPath,growthBookProperties,params,growthBookProperties.getRequestTimeout());
            //String response = HttpUtils.executeHttpRequest(HttpMethod.GET, hostUrl + fetchFeaturesPath + growthBookProperties.getApiKey(), null, null, params, null,requestTimeOut);
        }
        catch (Exception e) {
           throw e;
        }
        return response;
    }

    public String apiCallFeatures(String hostUrl, String fetchFeaturesPath, GrowthBookProperties growthBookProperties, Map<String, String> params, int requestTimeOut) {
        String apiKey = growthBookProperties.getApiKey();
        String url = hostUrl + fetchFeaturesPath + apiKey;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        if (params != null) {
            params.forEach(uriBuilder::queryParam);
        }
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                String.class
        );
        return responseEntity.getBody();
    }













}