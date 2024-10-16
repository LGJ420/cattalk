package com.example.cattalk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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



    
    // 닉네임으로 사용자 찾기 (현재 사용자 제외)
    public List<UserDTO> searchByNickname(Long currentUserId, String nickname) {
        List<User> users = userRepository.findByNicknameContainingAndIdNot(currentUserId, nickname);

        List<UserDTO> userDTOs = users.stream()
            .map(user -> UserDTO.builder()
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .build())
            .collect(Collectors.toList());

        return userDTOs;
    }




    // 회원가입
    public void signUp(UserDTO userDTO){

        if (userRepository.existsByUserId(userDTO.getUserId())) {

            throw new IllegalArgumentException("The username already exists.");
        }

        // 비밀번호를 암호화하여 저장
        User user = User.builder()
            .userId(userDTO.getUserId())
            .userPw(passwordEncoder.encode(userDTO.getUserPw()))
            .realname(userDTO.getRealname())
            .nickname(userDTO.getNickname())
            .role(userDTO.getRole())
            .signupDate(LocalDateTime.now())
            .build();

        userRepository.save(user);
    }





    // 회원정보 수정
    public void modifyUser(Long id, UserDTO userDTO){

        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("The specified user does not exist."));
        
        user
            .setUserPw(userDTO.getUserPw())
            .setRealname(userDTO.getRealname())
            .setNickname(userDTO.getNickname())
            .setProfileImage(userDTO.getProfileImage())
            .setPhone(userDTO.getPhone())
            .setBirth(userDTO.getBirth())
            .setPostcode(userDTO.getPostcode())
            .setPostAddress(userDTO.getPostAddress())
            .setDetailAddress(userDTO.getDetailAddress())
            .setExtraAddress(userDTO.getExtraAddress());

        userRepository.save(user);
    }

}
