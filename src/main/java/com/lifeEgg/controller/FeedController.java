package com.lifeEgg.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.FeedService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class FeedController {
	
	private final FeedService feedService;
    
    @RequestMapping(value="/feed")
    public String feed(HttpSession session, Model model){
    	
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            // 로그인 안됨
            return "redirect:/home";
        }

        model.addAttribute("user", loginUser);
        
        
        List<PostDTO> feedList = feedService.getFeedPosts(loginUser);

        model.addAttribute("feedPages", feedService.toFeedPageDTO(feedList));
        model.addAttribute("feedList", feedList);
        
        
        return "feed";
    }
}