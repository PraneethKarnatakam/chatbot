package com.zoomcar.ChatBot.service;


import com.zoomcar.ChatBot.configuration.GrowthBookProperties;
import growthbook.sdk.java.GBContext;
import growthbook.sdk.java.GrowthBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class GrowthBookManager {

    @Autowired
    private GrowthBookProperties growthBookProperties;

    private final String fetchFeaturesPath = "/api/features/";







}