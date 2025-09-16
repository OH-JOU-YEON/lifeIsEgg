package com.lifeEgg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.PostService;
import com.lifeEgg.service.UserService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class UserInfoController {

	private final UserService userService;
	private final PostService postService;
	
    @GetMapping(value="/user")
    public String userInfo(HttpSession session, Model model){
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            // 로그인 안됨
            return "redirect:/home";
        }

        // 로그인
        model.addAttribute("user", loginUser);
        return "user_info";
    }
    
    @PostMapping(value="/user/update")
    public String userInfoUpdate(HttpSession session, UserDTO user) {
    	userService.updateUser(user);
    	postService.updatePostAge(user);
    	session.setAttribute("loginUser", user);
    	return "redirect:/user";
    }
    
}