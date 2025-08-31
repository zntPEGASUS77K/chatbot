package com.chatbto.chatbot.controller;

import com.chatbto.chatbot.dto.MessageDto;
import com.chatbto.chatbot.service.ChatService;
import com.chatbto.chatbot.utils.MapperUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/session")
@CrossOrigin
public class SessionController {

    private final ChatService chatService;

    public SessionController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{sessionId}/messages")
    public List<MessageDto> getMessages(@PathVariable String sessionId) {
        return chatService.getMessagesBySession(sessionId)
                .stream()
                .map(MapperUtils::toDto)
                .collect(Collectors.toList());
    }
}
