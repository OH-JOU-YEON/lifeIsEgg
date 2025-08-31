package com.lifeEgg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lifeEgg.dto.UserDTO;


@Controller
public class UserInfoController {

	
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
    
    @PostMapping(value="/userUpdate")
    public String userUpdate() {
    	return "";
    }
    
    
}