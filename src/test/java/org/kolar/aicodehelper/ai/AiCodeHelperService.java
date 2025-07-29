package org.kolar.aicodehelper.ai;

import dev.langchain4j.service.SystemMessage;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

public interface AiCodeHelperService {
    @SystemMessage(fromResource = "system_prompt.txt")
   String chat(String userMessage);
}
