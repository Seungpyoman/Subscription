package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 API 경로 허용
                .allowedOriginPatterns("*") // 모든 Origin 허용 (RESTED 같은 브라우저 확장 포함)
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS") // Preflight 요청 허용
                .allowCredentials(true); // 인증정보(쿠키 등) 허용
    }
}


