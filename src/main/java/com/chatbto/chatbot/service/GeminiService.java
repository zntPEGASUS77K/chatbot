package com.chatbto.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeminiService {

    private final WebClient webClient;
    private final String apiKey;

    public GeminiService(WebClient.Builder builder, @Value("${gemini.api.key}") String apiKey) {
        this.webClient = builder.baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent").build();
        this.apiKey = apiKey;
    }

    public Mono<String> generateResponse(String prompt) {
        String payload = """
            {
              "contents": [
                {
                  "parts": [
                    {
                      "text": "%s"
                    }
                  ]
                }
              ]
            }
            """.formatted(prompt);

        return webClient.post()
                .uri("?key=" + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    int indexStart = response.indexOf("\"text\":\"") + 8;
                    int indexEnd = response.indexOf("\"", indexStart);
                    return response.substring(indexStart, indexEnd).replace("\\n", "\n");
                });
    }
}
