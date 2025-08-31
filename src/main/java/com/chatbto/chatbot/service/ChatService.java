package com.chatbto.chatbot.service;

import com.chatbto.chatbot.entity.ChatMessage;
import com.chatbto.chatbot.entity.ChatSession;
import com.chatbto.chatbot.repository.ChatMessageRepository;
import com.chatbto.chatbot.repository.ChatSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatSessionRepository sessionRepo;
    private final ChatMessageRepository messageRepo;
    private final GeminiService geminiService;

    public ChatService(ChatSessionRepository sessionRepo, ChatMessageRepository messageRepo, GeminiService geminiService) {
        this.sessionRepo = sessionRepo;
        this.messageRepo = messageRepo;
        this.geminiService = geminiService;
    }

    public ChatSession getOrCreateSession(String sessionId) {
        ChatSession session = sessionRepo.findBySessionId(sessionId);
        if (session == null) {
            session = new ChatSession(sessionId);
            sessionRepo.save(session);
        }
        return session;
    }

    public List<ChatMessage> getMessagesBySession(String sessionId) {
        return messageRepo.findBySessionIdOrderByTimestamp(sessionId);
    }

    public ChatMessage saveMessage(ChatSession session, String content, ChatMessage.MessageType type) {
        ChatMessage message = new ChatMessage(session, content, type);
        return messageRepo.save(message);
    }

    public String handleUserMessage(String sessionId, String message) {
        ChatSession session = getOrCreateSession(sessionId);
        saveMessage(session, message, ChatMessage.MessageType.USER);

        String response = geminiService.generateResponse(message).block(); // sync for now
        saveMessage(session, response, ChatMessage.MessageType.ASSISTANT);

        return response;
    }
}
