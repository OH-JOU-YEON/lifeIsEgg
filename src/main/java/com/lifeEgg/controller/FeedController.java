package com.lifeEgg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lifeEgg.dto.UserDTO;


@Controller
public class FeedController {
    
    @RequestMapping(value="/feed")
    public String feed(HttpSession session, Model model){
    	
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            // 로그인 안됨
            return "redirect:/home";
        }

        model.addAttribute("user", loginUser);
        return "feed";
    }
}