package com.lifeEgg.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.security.CustomUserDetails;
import com.lifeEgg.service.PostService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class DiaryDetailController {
	
	private final PostService postService; 
    
    @RequestMapping(value="/diary/{uuid}")
    public String diaryDetail(@PathVariable String uuid, Model model){
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
        if (auth == null || !auth.isAuthenticated()) {
            // 로그인 안됨
            return "redirect:/home";
        }
    	
        CustomUserDetails detail = (CustomUserDetails) auth.getPrincipal();
        UserDTO user = detail.toUserDTO();
        
        model.addAttribute("uuid",uuid);
        model.addAttribute("user", user);
        
        //uuid에 맞는 포스트 검색해와서 보내주기 
        
        PostDTO postDTO = postService.getPostByUuid(uuid); 
        
        //삭제하거나 응원을 보내기 위해 로그인 유저와 포스트 작성자가 일치하는지 일치하지 않는지 검사 
        
        if(postDTO.getUser_id() == user.getId()) {
        	model.addAttribute("cheerable",false);
        } else {
        	model.addAttribute("cheerable",true); 
        }
        
        model.addAttribute("post", postDTO);
        return "diary";
    }
}