package com.chatbto.chatbot.repository;

import com.chatbto.chatbot.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, Long> {
    ChatSession findBySessionId(String sessionId);
}