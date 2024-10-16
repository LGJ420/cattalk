package com.example.cattalk.service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.cattalk.document.Chat;
import com.example.cattalk.repository.ChatRepository;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Flux<Chat> getMessage(String sender, String receiver){

        return chatRepository.mFindBySender(sender, receiver)
            .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Chat> setMessage(Chat chat){

        chat.setCreatedAt(LocalDateTime.now());
        
        return chatRepository.save(chat);
    }
}