package com.chatbto.chatbot.repository;
import com.chatbto.chatbot.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query("SELECT m FROM ChatMessage m WHERE m.chatSession.sessionId = :sessionId ORDER BY m.timestamp ASC")
    List<ChatMessage> findBySessionIdOrderByTimestamp(String sessionId);
}
