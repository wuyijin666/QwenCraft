package org.kolar.aicodehelper.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeHelperServiceTest {
    @Resource
    private AiCodeHelperService aiCodeHelperService;

    @Test
    void chat() {
        String res = aiCodeHelperService.chat(1,"你好，我是程序员kolar");
        System.out.println(res);
    }

    @Test
    void chatWithMemory() {
        String res = aiCodeHelperService.chat(1, "你好，我是程序员koalr！");
        System.out.println(res);
        res = aiCodeHelperService.chat(1, "诶, 我说我是谁来着？");
        System.out.println(res);

        String res2 = aiCodeHelperService.chat(2, "hello im programmer lemon");
        System.out.println(res2);
    }

    @Test
    void chatWithSystem() {
        String userMessage = "你好，我是程序员mora！请为我提供最常见的6条JVM八股";
        AiCodeHelperService.Report report = aiCodeHelperService.chatForReport(userMessage);
        System.out.println(report);

        // 验证Report类被哪类类加载器加载
//        Class<?> reportClass = AiCodeHelperService.Report.class;
//        ClassLoader classLoader = reportClass.getClassLoader();
//        System.out.println("Report class was loaded by:" + classLoader);
    }

    @Test
    void chatWithRag() {
        String res = aiCodeHelperService.chat(3, "你好，我是程序员mora！请为我提供最常见的6条JVM八股");
        System.out.println(res);
    }

    @Test
    void chatWithMCP() {
        String res = aiCodeHelperService.chat(3, "你好，我是程序员mora！请为我提供最常见的6条提升人生格局的话");
        System.out.println(res);
    }
}