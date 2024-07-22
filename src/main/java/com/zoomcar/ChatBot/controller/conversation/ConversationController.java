package com.zoomcar.ChatBot.controller.conversation;

import com.zoomcar.ChatBot.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ConversationController {

    @Autowired
    private ConversationService service;

    @PostMapping
    public void saveConversation(@RequestParam Long userId, @RequestParam String userMessage, @RequestParam String botResponse) {
        service.saveConversation(userId, userMessage, botResponse);
    }

}

