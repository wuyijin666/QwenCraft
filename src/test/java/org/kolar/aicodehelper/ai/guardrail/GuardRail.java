package org.kolar.aicodehelper.ai.guardrail;


import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.guardrail.InputGuardrail;
import dev.langchain4j.guardrail.InputGuardrailResult;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * 检测用户输入是否安全
 */
public class GuardRail implements InputGuardrail {

    // 敏感词集合
    private static final Set<String> sentitiveWords = Set.of("kill", "evil");

    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        // 获取用户输入，并且转换为小写，确保大小写不敏感
        String inputText = userMessage.singleText().toLowerCase();
        // 使用正则表达式，切割用户文本为单词
        String[] words = inputText.split("\\W+");
        for(String word : words){
            if(sentitiveWords.contains(word)){
                // 失败返回 不再调用ai
                return fatal("inputText contains sentitive word :" + word);
            }
        }
        return success();
    }
}
