package com.chatbto.chatbot.controller;

import com.chatbto.chatbot.dto.ChatRequest;
import com.chatbto.chatbot.dto.ChatResponse;
import com.chatbto.chatbot.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String response = chatService.handleUserMessage(request.getSessionId(), request.getMessage());
        return new ChatResponse(response);
    }
}
