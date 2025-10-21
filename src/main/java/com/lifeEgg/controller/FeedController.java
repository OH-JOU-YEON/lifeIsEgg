package com.lifeEgg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.security.CustomUserDetails;
import com.lifeEgg.service.FeedService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class FeedController {
	
	private final FeedService feedService;
    
    @RequestMapping(value="/feed")
    public String feed(HttpSession session, Model model,@RequestParam(required = false) Long page){
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
        if (auth == null || !auth.isAuthenticated()) {
            // 로그인 안됨
            return "redirect:/home";
        }
    	
        CustomUserDetails detail = (CustomUserDetails) auth.getPrincipal();
        UserDTO user = detail.toUserDTO();
        
        if(page == null) {
        	page = (long) 0; 
        }
        
        List<PostDTO> oldFeed = (List<PostDTO>) session.getAttribute("oldFeed");
        List<PostDTO> feedList = new ArrayList<>();
        
        int len = 5; //겹치지 않도록 저장해두는 갯수

    	if (oldFeed == null) {
        	oldFeed = new ArrayList<>();
        	feedList = feedService.getFeedPosts(user);
        } else {
        	feedList = feedService.getFeedPosts(user, oldFeed);
        	if (feedList == null) {
        		oldFeed.clear();
        		feedList = feedService.getFeedPosts(user);
        	}
        }
    	
        model.addAttribute("feedList", feedList);
    	oldFeed.addAll(feedList);
    	
    	if (oldFeed.size() > len) {
    		oldFeed.subList(0, oldFeed.size() - len - 1).clear();
    	}
        
        session.setAttribute("oldFeed", oldFeed);
        
        return "feed";
    }
    
}