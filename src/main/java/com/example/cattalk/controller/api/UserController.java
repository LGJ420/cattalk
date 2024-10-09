package com.example.cattalk.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cattalk.dto.UserDTO;
import com.example.cattalk.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO) {

        userService.signUp(userDTO);

        return ResponseEntity.ok("Sign-up completed");
    }
}
