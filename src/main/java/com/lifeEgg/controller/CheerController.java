package com.lifeEgg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lifeEgg.dto.UserDTO;

@Controller 
public class CheerController {
	
	@GetMapping(value = "/cheer")
	public String writeCheer( Model model, HttpSession session) {
		
		 UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
	        if (loginUser == null) {
	            // 로그인 안됨
	            return "redirect:/home";
	        }

	        model.addAttribute("user", loginUser);
		
		
		return "cheer-write";
	}
	
	@PostMapping("/create/cheer")
	public void createCheer(HttpServletRequest request) {
		
		
	}
	

}
