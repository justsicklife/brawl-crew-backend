package com.gamecrew.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/posts","/", "/login", "/oauth2/**").permitAll()  // 인증 없이 접근 가능한 경로
                        .anyRequest().authenticated()  // 그 외 모든 요청은 인증 필요
                );

        return http.build();
    }


}
