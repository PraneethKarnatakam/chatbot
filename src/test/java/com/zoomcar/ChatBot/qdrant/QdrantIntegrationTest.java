package com.zoomcar.ChatBot.qdrant;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import io.qdrant.client.grpc.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.qdrant.QdrantContainer;

import java.util.concurrent.ExecutionException;

import static dev.langchain4j.internal.Utils.randomUUID;

public class QdrantIntegrationTest {

    private static int grpcPort = 6334;
    private static String collectionName = "langchain4j-" + randomUUID();
    private static Collections.Distance distance = Collections.Distance.Cosine;
    private static int dimension = 384;
    private static QdrantContainer qdrantContainer;
    private static QdrantClient qdrantClient;
    private static EmbeddingModel embeddingModel;
    private static EmbeddingStore<TextSegment> embeddingStore;


    @BeforeAll
    public static void setUpContainer() throws ExecutionException, InterruptedException {
        qdrantContainer = new QdrantContainer("qdrant/qdrant:latest");
        qdrantContainer.start();
        qdrantClient =
                new QdrantClient(
                        QdrantGrpcClient.newBuilder(qdrantContainer.getHost(), qdrantContainer.getMappedPort(grpcPort), false)
                                .build());
        qdrantClient.createCollectionAsync(
                        collectionName,
                        Collections.VectorParams.newBuilder().setDistance(distance).setSize(dimension).build())
                .get();
        embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        embeddingStore =
                QdrantEmbeddingStore.builder()
                        .host(qdrantContainer.getHost())
                        .port(qdrantContainer.getMappedPort(grpcPort))
                        .collectionName(collectionName)
                        .build();
    }

    @Test
    void testQdrantCapabilities() {

        TextSegment segment1 = TextSegment.from("I've been to France twice.");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        embeddingStore.add(embedding1, segment1);



    }






    @Test
    public void


}
