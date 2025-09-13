package com.lifeEgg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.FeedService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class FeedController {
	
	private final FeedService feedService;
    
    @RequestMapping(value="/feed")
    public String feed(HttpSession session, Model model,@RequestParam(required = false) Long page){
    	
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            // 로그인 안됨
            return "redirect:/home";
        }
        if(page == null) {
        	page = (long) 0; 
        }

        model.addAttribute("user", loginUser);
        
        List<PostDTO> oldFeed = (List<PostDTO>) session.getAttribute("oldFeed");
        List<PostDTO> feedList = new ArrayList<>();
        
        int len = 5; //겹치지 않도록 저장해두는 갯수

    	if (oldFeed == null) {
        	oldFeed = new ArrayList<>();
        	feedList = feedService.getFeedPosts(loginUser);
        } else {
        	feedList = feedService.getFeedPosts(loginUser, oldFeed);
        	if (feedList == null) {
        		oldFeed.clear();
        		feedList = feedService.getFeedPosts(loginUser);
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