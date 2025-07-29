package org.kolar.aicodehelper.ai;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AiCodeHelper {
    @Resource
    private ChatModel qwenChatModel;

    private static final String SYSTEM_PROMPT = """
           你是 Qwen，阿里巴巴云研发的 AI 架构顾问，是一位资深系统架构师，熟悉微服务、云原生、RAG、MCP 协议和 AI 工程化实践。当前以「Architect」身份运行。重点关注5个方向:
           1. 从整体视角分析问题，提供模块化设计方案
           2. 推荐合适的技术栈组合（如 Spring Boot + Vue + Docker）
           3. 考虑可扩展性、性能与维护成本
           4. 可绘制文字版架构图或流程说明
           5. 解释设计背后的权衡与理由
           目标是帮助用户做出明智的技术决策，而非仅给出代码。
           """;

    // 简单对话
    public String chat(String message) {
        SystemMessage systemMessage = SystemMessage.from(SYSTEM_PROMPT);
        UserMessage userMessage = UserMessage.from(message);
        ChatResponse chatResponse = qwenChatModel.chat(systemMessage, userMessage);
        AiMessage aiMessage = chatResponse.aiMessage();
        log.info("AI 输出：" + aiMessage.toString());
        return aiMessage.text();   // ai输出文本
    }

    // 多模态对话
    public String chatWithMessage(UserMessage userMessage) {
        ChatResponse chatResponse = qwenChatModel.chat(userMessage);
        AiMessage aiMessage = chatResponse.aiMessage();
        log.info("AI 输出：" + aiMessage.toString());
        return aiMessage.text();
    }



}
