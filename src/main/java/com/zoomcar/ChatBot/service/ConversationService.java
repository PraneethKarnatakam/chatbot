package com.zoomcar.ChatBot.service;


import com.zoomcar.ChatBot.entity.Conversation;
import com.zoomcar.ChatBot.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    public Conversation saveConversation(Long userId, String userMessage, String botResponse) {
        Conversation conversation = new Conversation();
        conversation.setUserId(userId);
        conversation.setUserMessage(userMessage);
        conversation.setBotResponse(botResponse);
        conversation.setTimestamp(LocalDateTime.now());
        return conversationRepository.save(conversation);
    }





}
