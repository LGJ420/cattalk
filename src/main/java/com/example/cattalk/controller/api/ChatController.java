package com.example.cattalk.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.cattalk.service.ChatService;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;


}