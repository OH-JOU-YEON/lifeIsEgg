package com.lifeEgg.controller;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.security.CustomUserDetails;
import com.lifeEgg.service.PostService;
import com.lifeEgg.service.UserService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class UserInfoController {

	private final UserService userService;
	private final PostService postService;
	
    @GetMapping(value="/user")
    public String userInfo(Model model){
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
        if (auth == null || !auth.isAuthenticated()) {
            // 로그인 안됨
            return "redirect:/home";
        }
    	
        CustomUserDetails detail = (CustomUserDetails) auth.getPrincipal();
        UserDTO user = detail.toUserDTO();

        // 로그인
        model.addAttribute("user", user);
        return "user_info";
    }
    
    @PostMapping(value="/user/update")
    public String userInfoUpdate(UserDTO user) {
    	userService.updateUser(user);
    	postService.updatePostAge(user);
    	
    	CustomUserDetails detail = user.toCustomUserDetails();
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    	Authentication newAuth = 
    		    new UsernamePasswordAuthenticationToken(detail, auth.getCredentials(), detail.getAuthorities());

    	SecurityContextHolder.getContext().setAuthentication(newAuth);
        
    	return "redirect:/user";
    }
    
}