package com.chatbto.chatbot.dto;

import lombok.Data;

@Data
public class ChatRequest {
    private String sessionId;
    private String message;
}
