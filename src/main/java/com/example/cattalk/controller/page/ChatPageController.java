package com.example.cattalk.controller.page;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.cattalk.entity.User;

@Controller
public class ChatPageController {

    @GetMapping("/chat/{nickname}")
    public String getChatPage(
        @AuthenticationPrincipal User user,
        @PathVariable String nickname,
        Model model) {

        // String senderNickname = user.getNickname();
        // 현재는 GM기정 유저로 하드코딩
        String senderNickname = "GM기정";
        String receiverNickname = nickname;

        model.addAttribute("senderNickname", senderNickname);
        model.addAttribute("receiverNickname", receiverNickname);

        return "chat/chat";
    }
}
