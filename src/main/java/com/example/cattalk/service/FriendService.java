package com.example.cattalk.service;

import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.cattalk.dto.UserDTO;
import com.example.cattalk.entity.Friend;
import com.example.cattalk.entity.User;
import com.example.cattalk.repository.FriendRepository;
import com.example.cattalk.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    // 친구 목록 조회
    public List<UserDTO> getFriends(String userNickname) {
        User user = userRepository.findByNickname(userNickname)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Friend> friends = friendRepository.findByUser(user);

        List<UserDTO> userDTOs = friends.stream()
            .map(friend -> UserDTO.builder()
                .nickname(friend.getFriend().getNickname())
                .profileImage(friend.getFriend().getProfileImage())
                .build())
            .collect(Collectors.toList());

        return userDTOs;
    }


    // 친구 추가
    public void addFriend(String userNickname, String friendNickname) {
        User user = userRepository.findByNickname(userNickname)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        User friend = userRepository.findByNickname(friendNickname)
            .orElseThrow(() -> new IllegalArgumentException("Friend not found"));

        Friend newFriend = Friend.builder()
            .user(user)
            .friend(friend)
            .addedAt(LocalDateTime.now())
            .build();
            
        friendRepository.save(newFriend);
    }


    // 친구 삭제
    public void removeFriend(String userNickname, String friendNickname) {
        User user = userRepository.findByNickname(userNickname)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        User friend = userRepository.findByNickname(friendNickname)
            .orElseThrow(() -> new IllegalArgumentException("Friend not found"));
        Friend friendship = friendRepository.findByUserAndFriend(user, friend)
            .orElseThrow(() -> new IllegalArgumentException("Friendship not found"));

        friendRepository.delete(friendship);
    }
}
