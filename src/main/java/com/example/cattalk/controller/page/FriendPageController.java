package com.example.cattalk.controller.page;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cattalk.dto.UserDTO;
import com.example.cattalk.entity.User;
import com.example.cattalk.service.FriendService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class FriendPageController {
    
    private final FriendService friendService;

    @GetMapping("friends")
    public String getFriendPage(
        Model model,
        @AuthenticationPrincipal User user) {

        // 현재는 GM기정 유저로 하드코딩
        List<UserDTO> friends = friendService.getFriends("GM기정");

        model.addAttribute("friends", friends);

        return "chat/friends";
    }
    
    
}
