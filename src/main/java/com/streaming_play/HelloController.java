package com.streaming_play;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final StreamingPlayAiService aiService;

    public HelloController(StreamingPlayAiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/")
    public String hello() {
        return this.aiService.generateGreeting();
    }
}
