package com.lifeEgg.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.security.CustomUserDetails;


@Controller
public class HomeController {
    
    @GetMapping(value="/home")
    public String home(){
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
        if (auth == null || !auth.isAuthenticated()) {
            // 로그인 안됨
            return "home";
        }
    	
        return "redirect:/feed";
    }
}