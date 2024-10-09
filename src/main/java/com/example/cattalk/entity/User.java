package com.example.cattalk.entity;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) // 체이닝 방식의 Setter 사용
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // Setter 방지
    private Long id;

    @Column(nullable = false, unique = true)
    @Setter(AccessLevel.NONE) // Setter 방지
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String realname;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter(AccessLevel.NONE) // Setter 방지
    private UserRole role;

    @Setter(AccessLevel.NONE) // Setter 방지
    private LocalDateTime signupDate;

    private String profileImage;

    @Column(length = 11)
    private String phone;

    @Column(length = 6)
    private String birth;

    @Column(length = 5)
    private String postcode; // 우편번호

    private String postAddress; // 경기도 성남시
    private String detailAddress; // 101동 505호
    private String extraAddress; // 부수사항

    // username 명칭 대체
    @Override
    public String getUsername() {
        return userId;  // userId를 username으로 사용
    }

    // password 명칭 대체
    @Override
    public String getPassword() {
        return userPw;  // userPw를 password로 사용
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자의 권한을 반환
        return Collections.singleton(() -> "ROLE_" + role.name());
    }
    
    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되지 않았음을 반환
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠기지 않았음을 반환
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명이 만료되지 않았음을 반환
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정이 활성화되어 있음을 반환
        return true;
    }
}
