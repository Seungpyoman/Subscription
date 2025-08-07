package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 전체 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/api/**").permitAll() // H2 + REST API 모두 허용
                        .anyRequest().authenticated() // 그 외는 인증 필요 (원한다면 permitAll로 바꿔도 됨)
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable()) // H2 콘솔 frame 허용
                );

        return http.build();
    }
}
