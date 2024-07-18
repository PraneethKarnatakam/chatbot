package com.zoomcar.ChatBot.controller.qdrant;

import com.zoomcar.ChatBot.service.QdrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QdrantTestController {

    @Autowired
    private QdrantService qdrantService;



}
