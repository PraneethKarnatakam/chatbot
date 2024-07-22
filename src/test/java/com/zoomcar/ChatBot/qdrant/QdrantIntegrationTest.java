package com.zoomcar.ChatBot.qdrant;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import io.qdrant.client.grpc.Collections;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.qdrant.QdrantContainer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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

        addTextSegmentToStore("I've been to France twice.");
        addTextSegmentToStore("The weather is so cool");
        addTextSegmentToStore("Zoom car is a self-driving car rental marketplace");
        addTextSegmentToStore("Ooty is monsoon's are good");

        assertEmbedding("Did you ever travel abroad?", "I've been to France twice.");
        assertEmbedding("How's is Temperature?", "The weather is so cool");
        assertEmbedding("Looking for car rental?", "Zoom car is a self-driving car rental marketplace");
        assertEmbedding("How is Ooty whether ?", "Ooty is monsoon's are good",2);
        assertEmbedding("How is Ooty whether ?", "The weather is so cool",2);
    }

    private void assertEmbedding(String query, String expected, int relavence) {
        Embedding queryEmbedding = embeddingModel.embed(query).content();
        List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, relavence);
        List<String> relavantDocuments = relevant.stream().map(ele -> ele.embedded().text()).collect(Collectors.toList());
        assertTrue(relavantDocuments.stream().anyMatch(doc -> doc.equals(expected)));
    }

    private void assertEmbedding(String query, String expected) {
        Embedding queryEmbedding = embeddingModel.embed(query).content();
        List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, 1);
        EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);
        assertEquals(expected, embeddingMatch.embedded().text());
    }


    private void addTextSegmentToStore(String text) {
        TextSegment segment = TextSegment.from(text);
        Embedding embedding = embeddingModel.embed(segment).content();
        embeddingStore.add(embedding, segment);
    }

    @AfterAll
    public static void tearDownContainer() {
        qdrantContainer.stop();
    }

}
