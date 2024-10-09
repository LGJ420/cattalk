package com.example.cattalk.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cattalk.dto.UserDTO;
import com.example.cattalk.entity.User;
import com.example.cattalk.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public void signUp(UserDTO userDTO){

        if (userRepository.existsByUserId(userDTO.getUserId())) {

            throw new IllegalArgumentException("The username already exists.");
        }

        // 비밀번호를 암호화하여 저장
        User user = User.builder()
            .userId(userDTO.getUserId())
            .userPw(passwordEncoder.encode(userDTO.getUserPw()))
            .nickname(userDTO.getNickname())
            .role(userDTO.getRole())
            .build();

        userRepository.save(user);

    }
}
