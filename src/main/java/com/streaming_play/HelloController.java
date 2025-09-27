package com.streaming_play;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final String platform;
    private final StreamingPlayAiService aiService;

    public HelloController(StreamingPlayAiService aiService, @Value("${spring.application.name}")String platform) {
        this.aiService = aiService;
        this.platform = platform;
    }

    @GetMapping("/")
    public String hello() {
        return this.aiService.generateGreeting(platform);
    }
}
