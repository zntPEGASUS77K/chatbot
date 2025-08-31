package com.chatbto.chatbot.utils;

import com.chatbto.chatbot.dto.MessageDto;
import com.chatbto.chatbot.entity.ChatMessage;

import java.time.format.DateTimeFormatter;

public class MapperUtils {
    public static MessageDto toDto(ChatMessage message) {
        return new MessageDto(
                message.getContent(),
                message.getType().name(),
                message.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }
}
