package org.kolar.aicodehelper.controller;

import jakarta.annotation.Resource;
import org.kolar.aicodehelper.ai.AiCodeHelperService;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AiController {

    @Resource
    AiCodeHelperService  aiCodeHelperService;

    // 流式响应SSE
    @GetMapping("/chat")
    public Flux<ServerSentEvent<String>> chat(int memoryId, String message){
        return aiCodeHelperService.chatStream(memoryId, message)
                .map(chunk -> ServerSentEvent.<String>builder()
                        .data(chunk)
                        .build());
    }
}
