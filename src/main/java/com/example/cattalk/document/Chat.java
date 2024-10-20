package com.example.cattalk.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "chat")
public class Chat {
    
    @Id
    private String id;

    private String msg;
    private String sender;
    private String receiver;
    private String room;

    // TTL을 설정해주었으나
    // 어째서인지 우분투에 설치한 몽고DB에서는
    // 캡드컬렉션과 TTL을 동시에 설정하지 못한다고함
    // 그래서 TTL 설정을 지웠음
    // @Indexed(expireAfterSeconds = 86400)  // 24시간 후 자동 삭제
    private LocalDateTime createdAt;
}
