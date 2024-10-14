package com.example.cattalk.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cattalk.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUserId(String userId);
    Optional<User> findByNickname(String nickname);
    boolean existsByUserId(String userId);

    // 현재 사용자 ID를 제외하고 닉네임 검색
    @Query("SELECT u FROM User u WHERE u.id <> :userId AND u.nickname LIKE %:nickname%")
    List<User> findByNicknameContainingAndIdNot(Long userId, String nickname);
}
