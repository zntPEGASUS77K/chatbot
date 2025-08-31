package com.chatbto.chatbot.service;

import com.chatbto.chatbot.entity.ChatSession;
import com.chatbto.chatbot.repository.ChatSessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final ChatSessionRepository sessionRepository;

    public SessionService(ChatSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public ChatSession getOrCreateSession(String sessionId) {
        ChatSession session = sessionRepository.findBySessionId(sessionId);
        if (session == null) {
            session = new ChatSession(sessionId);
            sessionRepository.save(session);
        }
        return session;
    }
}
