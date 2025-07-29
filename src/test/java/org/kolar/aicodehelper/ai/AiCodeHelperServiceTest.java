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
        String res = aiCodeHelperService.chat("你好，我是程序员kolar");
        System.out.println(res);
    }
}