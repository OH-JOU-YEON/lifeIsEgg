package com.lifeEgg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lifeEgg.dto.UserDTO;


@Controller
public class HomeController {
    
    @RequestMapping(value="/home")
    public String home(HttpSession session, Model model){
    	
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            // 로그인 안됨
            return "home";
        }

        // 로그인(정보 넘어가는지 확인하기 위해 임시로 userInfo로 걸어둠)
        model.addAttribute("user", loginUser);
        return "redirect:/user";
    }
}