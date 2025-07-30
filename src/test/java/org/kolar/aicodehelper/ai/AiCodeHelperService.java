package org.kolar.aicodehelper.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

import java.util.List;

public interface AiCodeHelperService {
    @SystemMessage(fromResource = "system_prompt.txt")
   String chat(@MemoryId int memoryId, @UserMessage String userMessage);

    @SystemMessage(fromResource = "system_prompt.txt")
    Report chatForReport(String userMessage);
    // 学习报告
    record Report(String name, List<String> suggestionList){}

}
