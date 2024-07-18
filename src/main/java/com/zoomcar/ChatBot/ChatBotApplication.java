package com.zoomcar.ChatBot;

import com.zoomcar.ChatBot.configuration.QdrantProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(QdrantProperties.class)
public class ChatBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatBotApplication.class, args);
	}

}
