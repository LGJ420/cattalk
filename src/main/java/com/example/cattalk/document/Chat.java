package com.example.cattalk.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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

    @Indexed(expireAfterSeconds = 86400)  // 24시간 후 자동 삭제
    private LocalDateTime createdAt;
}
