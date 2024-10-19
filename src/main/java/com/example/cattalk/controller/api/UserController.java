package com.example.cattalk.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cattalk.dto.UserDTO;
import com.example.cattalk.entity.User;
import com.example.cattalk.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    // 본인을 제외한 회원검색
    @GetMapping
    public ResponseEntity<List<UserDTO>> searchUsersByNickname(
        @AuthenticationPrincipal User currentUser,
        @RequestParam String nickname) {
        
        List<UserDTO> users = userService.searchByNickname(currentUser.getId(), nickname);
        
        return ResponseEntity.ok(users);
    }


    // 회원가입
    // @PostMapping
    // public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO) {

    //     userService.signUp(userDTO);

    //     return ResponseEntity.ok("Sign-up completed");
    // }


    // 회원정보 수정
    @PutMapping
    public ResponseEntity<String> modifyUser(
        @AuthenticationPrincipal User currentUser,
        @RequestBody UserDTO userDTO){

        userService.modifyUser(currentUser.getId(), userDTO);

        return ResponseEntity.ok("Modify completed");
    }

}
