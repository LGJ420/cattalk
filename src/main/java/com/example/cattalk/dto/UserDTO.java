package com.example.cattalk.dto;

import com.example.cattalk.entity.UserRole;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    
    // 회원가입 필수사항
    private String userId;
    private String userPw;
    private String realname;
    private String nickname;
    private UserRole role;

    // 추가 선택사항
    private String profileImage;
    private String phone;
    private String birth;
    private String postcode;
    private String postAddress;
    private String detailAddress;
    private String extraAddress;
}
