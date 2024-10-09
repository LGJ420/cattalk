package com.example.cattalk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.cattalk.security.handler.CustomAccessDeniedHandler;
import com.example.cattalk.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

// 개발 시에 적용하는 시큐리티 설정
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Profile("dev")
public class SecurityConfigDev {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests((requests) -> requests
                .anyRequest().permitAll()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .usernameParameter("userId")
                .passwordParameter("userPw")
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            )
            // 사용자가 접근권한이 없을경우 처리
            .exceptionHandling((config) -> {
                config.accessDeniedHandler(new CustomAccessDeniedHandler());
            })
            // userDetailsService가 구현되있으면 굳이 필요없다고 함
            // 여기서는 명시적으로 추가
            .userDetailsService(userDetailsServiceImpl);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 이렇게 PasswordEncoder와 UserDetailsService가 구현되어있으면
    // 스프링 시큐리티는 자동으로 DaoAuthenticationProvider를 설정하여 사용자 인증을 처리한다
}