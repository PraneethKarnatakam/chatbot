package com.zoomcar.ChatBot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import io.qdrant.client.grpc.Collections;

@Service
public class QdrantService {

    @Autowired
    private QdrantClient qdrantClient;

    private void embedded(String stringToEmbedded) {

    }

    private void getRevelaventDocs(String stringToEmbedded) {

    }

}
