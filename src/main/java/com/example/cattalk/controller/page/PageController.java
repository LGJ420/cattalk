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
public class PageController {
      
    private final FriendService friendService;


    // 친구 목록 페이지
    @GetMapping("friends")
    public String getFriendPage(
        Model model,
        @AuthenticationPrincipal User user) {

        List<UserDTO> friends = friendService.getFriends(user.getNickname());

        model.addAttribute("friends", friends);

        return "chat/friends";
    }
 

    // 친구 검색 페이지
    @GetMapping("search")
    public String getSearchPage() {

        return "chat/search";
    }
    

    // 설정 페이지
    @GetMapping("config")
    public String getConfigPage() {

        return "chat/config";
    }


    // 친구 관리 페이지
    @GetMapping("config/friends")
    public String getConfigFriendsPage() {

        return "chat/config_friends";
    }


    // 개인정보 설정 페이지
    @GetMapping("config/profile")
    public String getConfigProfilePage() {

        return "chat/config_profile";
    }









    /* ======== 기본 페이지 ======== */
    @GetMapping
    public String indexPage(){

        return "index";
    }

    @GetMapping("test")
    public String testPage() {

        return "test/test";
    }

    @GetMapping("test2")
    public String test2Page() {

        return "test/test2";
    }
    /* ======== 기본 페이지 ======== */

}
