package org.kolar.aicodehelper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局跨域配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 为所有请求添加跨域支持
                .allowedOrigins("*")    // 允许任何源
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的HTTP方法
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
