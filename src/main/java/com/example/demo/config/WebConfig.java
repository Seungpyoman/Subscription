package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 API 경로 허용
                .allowedOrigins("http://localhost:3000") // React 개발 서버
                .allowedMethods("GET","POST","PUT","DELETE") // 필요한 메서드 허용
                .allowCredentials(true); // 인증정보(쿠키 등) 허용
    }
}

