package com.example.cattalk.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import com.example.cattalk.document.Chat;

import reactor.core.publisher.Flux;

public interface ChatRepository extends ReactiveMongoRepository<Chat, String>{
    
    @Tailable // 커서를 안닫고 계속 유지시킨다, 이 설정을 하면 버퍼사이즈를 키워야한다
    @Query("{$or: [{sender: ?0, receiver: ?1}, {sender: ?1, receiver: ?0}]}")
    Flux<Chat> mFindBySender(String sender, String receiver);
    // Flux는 흐름, 데이터를 계속 흘려보낸다
}
