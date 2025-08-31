package com.chatbto.chatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDto {
    private String content;
    private String type;
    private String timestamp;
}
