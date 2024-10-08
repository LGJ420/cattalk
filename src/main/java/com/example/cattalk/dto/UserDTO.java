package com.example.cattalk.dto;

import com.example.cattalk.entity.UserRole;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    
    private String username;
    private String password;
    private String nickname;
    private UserRole role;
}
