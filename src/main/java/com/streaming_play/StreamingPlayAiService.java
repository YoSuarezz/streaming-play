package com.streaming_play;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface StreamingPlayAiService {

    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma de Streaming Play.
            Usa menos de 120 caracteres.
            """)
    String generateGreeting();
}
