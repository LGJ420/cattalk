package com.example.cattalk.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cattalk.entity.Friend;
import com.example.cattalk.entity.User;

public interface FriendRepository extends JpaRepository<Friend, Long>{
    
    List<Friend> findByUser(User user);
    Optional<Friend> findByUserAndFriend(User user, User friend);
}
