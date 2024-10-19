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



    public UserDTO getUser(Long id){

        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("The specified user does not exist."));
    
        UserDTO userDTO = UserDTO.builder()
            .userId(user.getUserId())
            .realname(user.getRealname())
            .nickname(user.getNickname())
            .role(user.getRole())
            .profileImage(user.getProfileImage())
            .phone(user.getPhone())
            .birth(user.getBirth())
            .postcode(user.getPostcode())
            .postAddress(user.getPostAddress())
            .detailAddress(user.getDetailAddress())
            .extraAddress(user.getExtraAddress())
            .build();

        return userDTO;
    }




    
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
        
        if (userDTO.getUserPw() != null && !userDTO.getUserPw().isEmpty()) {
            user.setUserPw(passwordEncoder.encode(userDTO.getUserPw()));
        }
        if (userDTO.getRealname() != null) {
            user.setRealname(userDTO.getRealname());
        }
        if (userDTO.getNickname() != null) {
            user.setNickname(userDTO.getNickname());
        }
        if (userDTO.getProfileImage() != null) {
            user.setProfileImage(userDTO.getProfileImage());
        }
        if (userDTO.getPhone() != null) {
            user.setPhone(userDTO.getPhone());
        }
        if (userDTO.getBirth() != null) {
            user.setBirth(userDTO.getBirth());
        }
        if (userDTO.getPostcode() != null) {
            user.setPostcode(userDTO.getPostcode());
        }
        if (userDTO.getPostAddress() != null) {
            user.setPostAddress(userDTO.getPostAddress());
        }
        if (userDTO.getDetailAddress() != null) {
            user.setDetailAddress(userDTO.getDetailAddress());
        }
        if (userDTO.getExtraAddress() != null) {
            user.setExtraAddress(userDTO.getExtraAddress());
        }

        userRepository.save(user);
    }

}
