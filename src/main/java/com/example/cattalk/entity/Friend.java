package com.example.cattalk.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

// 다대다 관계를 제거하기 위한 엔티티
@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 현재 유저

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend; // 친구 유저

    private LocalDateTime addedAt; // 친구 추가 시간
}