package com.zoomcar.ChatBot.postgresql;

import com.zoomcar.ChatBot.entity.Conversation;
import com.zoomcar.ChatBot.repository.ConversationRepository;
import com.zoomcar.ChatBot.service.ConversationService;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import io.qdrant.client.grpc.Collections;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.qdrant.QdrantContainer;
import org.testcontainers.utility.DockerImageName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ExecutionException;

@SpringBootTest
@ActiveProfiles("test")
public class ChatStoringIntegrationTest {

    //use the test container postgresql .
    //create the data

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ConversationService conversationService;

    private static PostgreSQLContainer postgreSQLContainer;

    @BeforeAll
    public static void setUpContainer() throws ExecutionException, InterruptedException {
       // postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15"));
        postgreSQLContainer = new PostgreSQLContainer<>(
                DockerImageName.parse("timescale/timescaledb:2.14.2-pg16").asCompatibleSubstituteFor("postgres")
        );

        postgreSQLContainer.start();
        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
    }

    @Test
    public void addData() {
        Conversation conversation1 = conversationService.saveConversation(1234L,"Test User Query", "Test Bot Response");
        Conversation conversation2 = conversationService.saveConversation(1235L,"Test User Query", "Test Bot Response");
        Conversation conversation3 = conversationService.saveConversation(1236L,"Test User Query", "Test Bot Response");
        var allConversations = conversationRepository.findAll();
        assertEquals(3,allConversations.size());
        var conversation3FromDatabase = conversationRepository.findById(3L);
        assertEquals(1236L,conversation3FromDatabase.get().getUserId());
    }

    @AfterAll
    public static void tearDownContainer() {
        postgreSQLContainer.stop();
    }

}
