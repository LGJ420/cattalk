package com.example.cattalk.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cattalk.entity.User;
import com.example.cattalk.service.FriendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend")
public class FriendController {

    private final FriendService friendService;

    // 타임리프로 쓰기위해 REST컨트롤러에서 임시 제외시킴
    // // 친구 목록 조회
    // @GetMapping
    // public ResponseEntity<List<UserDTO>> getFriends(
    //     @AuthenticationPrincipal User user) {

    //     List<UserDTO> friends = friendService.getFriends(user.getNickname());

    //     return ResponseEntity.ok(friends);
    // }
    

    // 친구 추가
    @PostMapping
    public ResponseEntity<String> addFriend(
        @AuthenticationPrincipal User user,
        @RequestParam("friendNickname") String friendNickname) {
        
        friendService.addFriend(user.getNickname(), friendNickname);

        return ResponseEntity.ok("Friend added successfully");
    }


    // 친구 삭제
    @DeleteMapping("/{nickname}")
    public ResponseEntity<String> removeFriend(
        @AuthenticationPrincipal User user,
        @PathVariable(name = "nickname") String friendNickname) {

        friendService.removeFriend(user.getNickname(), friendNickname);

        return ResponseEntity.ok("Friend removed successfully");
    }

}