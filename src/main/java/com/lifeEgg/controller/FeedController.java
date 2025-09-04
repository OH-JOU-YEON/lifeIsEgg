package com.lifeEgg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lifeEgg.dto.FeedPageDTO;
import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.PostService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class FeedController {
	
	private final PostService postService;
    
    @RequestMapping(value="/feed")
    public String feed(HttpSession session, Model model){
    	
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            // 로그인 안됨
            return "redirect:/home";
        }

        model.addAttribute("user", loginUser);
        
//        FeedPageDTO<PostDTO> posts = postService.getPostsByAge(0);
////        FeedPageDTO<PostDTO> posts = postService.getPostsByAge(loginUser.getAge());
//        System.out.println(posts.getContentList().get(0).getContent());
//        model.addAttribute("postPages", posts);
//        model.addAttribute("postList", posts.getContentList());
        
        
        return "feed";
    }
}