package com.example.cattalk.controller.page;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cattalk.dto.UserDTO;
import com.example.cattalk.entity.User;
import com.example.cattalk.service.FriendService;
import com.example.cattalk.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {
      
    private final FriendService friendService;
    private final UserService userService;


    // 친구 목록 페이지
    @GetMapping("friends")
    public String getFriendPage(
        Model model,
        @AuthenticationPrincipal User currentUser) {

        List<UserDTO> friends = friendService.getFriends(currentUser.getNickname());

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
    public String getConfigFriendsPage(
        Model model,
        @AuthenticationPrincipal User currentUser) {

        List<UserDTO> friends = friendService.getFriends(currentUser.getNickname());

        model.addAttribute("friends", friends);

        return "chat/config_friends";
    }


    // 개인정보 설정 페이지
    @GetMapping("config/profile")
    public String getConfigProfilePage(
        Model model,
        @AuthenticationPrincipal User currentUser) {

        UserDTO userDTO = userService.getUser(currentUser.getId());
        
        model.addAttribute("realname", userDTO.getRealname());
        model.addAttribute("nickname", userDTO.getNickname());
        model.addAttribute("phone", userDTO.getPhone());
        model.addAttribute("detailAddress", userDTO.getDetailAddress());

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
