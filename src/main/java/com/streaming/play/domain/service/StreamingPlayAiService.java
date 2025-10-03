package com.streaming.play.domain.service;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface StreamingPlayAiService {

    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma de Gestion de Películas {{platform}}.
            Usa menos de 120 caracteres.
            """)
    String generateGreeting(@V("platform") String platform);
}
