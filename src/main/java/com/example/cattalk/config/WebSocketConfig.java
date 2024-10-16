// package com.example.cattalk.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.socket.config.annotation.EnableWebSocket;
// import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
// import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// import com.example.cattalk.handler.WebSocketChatHandler;

// import lombok.RequiredArgsConstructor;

// @Configuration
// @RequiredArgsConstructor
// @EnableWebSocket
// public class WebSocketConfig implements WebSocketConfigurer {

//     private final WebSocketChatHandler webSocketChatHandler;

//     @Override
//     public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//         registry.addHandler(webSocketChatHandler, "ws/chat").setAllowedOrigins("*");
//     }
// }

// // 웹소켓 사용시