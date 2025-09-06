package com.lifeEgg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lifeEgg.dto.CheerDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.dto.cheerWriteDTO;
import com.lifeEgg.service.CheerService;
import com.lifeEgg.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class CheerController {
	
	private final CheerService cheerService; 
	private final PostService postService; 
	
	
	
	@GetMapping(value = "/cheer/{diaryUuid}")
	public String writeCheer( Model model, HttpSession session, @PathVariable String diaryUuid) {
		
		 UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
	        if (loginUser == null) {
	            // 로그인 안됨
	            return "redirect:/home";
	        }

	        model.addAttribute("user", loginUser);
	        model.addAttribute("diaryUuid",diaryUuid); 
		
		
		return "cheer-write";
	}
	
	@PostMapping("/create/cheer")
	public void createCheer(HttpServletRequest request,@RequestBody cheerWriteDTO cheerWriteDTO) {
		
		CheerDTO cheerDTO = new CheerDTO(); 
		
		
		
		
	}
	

}
