package com.lifeEgg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.PostService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class DiaryDetailController {
	
	private final PostService postService; 
    
    @RequestMapping(value="/diary/{uuid}")
    public String diaryDetail(@PathVariable String uuid, HttpSession session, Model model){
    	
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            // 로그인 안됨
            return "redirect:/home";
        }
        model.addAttribute("uuid",uuid);
        model.addAttribute("user", loginUser);
        
        //uuid에 맞는 포스트 검색해와서 보내주기 
        
        PostDTO postDTO = postService.getPostByUuid(uuid); 
        
        model.addAttribute("post", postDTO);
        return "diary";
    }
}