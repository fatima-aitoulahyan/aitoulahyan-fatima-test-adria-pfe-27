package org.example.ebankbot.controllers;

import org.example.ebankbot.agents.EbankAIChat;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/ebank")
public class EbankChatBotController {

    private final EbankAIChat ebankAIChat;

    // Injection par constructeur
    public EbankChatBotController(EbankAIChat ebankAIChat) {
        this.ebankAIChat = ebankAIChat;
    }

    // Chat classique
    @GetMapping("/chat")
    public String chat(
            @RequestParam String query,
            @RequestParam(defaultValue = "user-1") String conversationId) {

        return ebankAIChat.chat(
                new Prompt(query),
                conversationId
        );
    }

    // Chat avec streaming
    @GetMapping(value = "/chatStream",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(
            @RequestParam String query,
            @RequestParam(defaultValue = "user-1") String conversationId) {

        return ebankAIChat.chatStream(
                new Prompt(query),
                conversationId
        );
    }
}