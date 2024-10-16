package com.example.cattalk.controller.api;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.cattalk.document.Chat;
import com.example.cattalk.service.ChatService;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // produces = MediaType.TEXT_EVENT_STREAM_VALUE 이렇게 설정해주면
    // SSE프로토콜이 된다
    // 착각하면 안되는게 SSE프로토콜은
    // 서버에서 클라이언트`로 `실시간` 이벤트를 전송하는 "단방향!"이다
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMessage(@RequestParam String sender, @RequestParam String receiver) {

        return chatService.getMessage(sender, receiver);
    }

    @PostMapping
    public Mono<Chat> setMessage(@RequestBody Chat chat) {
        
        return chatService.setMessage(chat);
    }
}