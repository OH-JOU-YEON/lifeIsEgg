package com.lifeEgg.controller;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lifeEgg.dto.DiaryWriteDTO;
import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.PostService;

import lombok.RequiredArgsConstructor;


@Controller 
@RequiredArgsConstructor
public class WriteDiaryController {
	
	private final PostService postService;
	
	@GetMapping(value = "/write")
	public String writeDiary( Model model, HttpSession session) {
		
		 UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
	        if (loginUser == null) {
	            // 로그인 안됨
	            return "redirect:/home";
	        }

	        model.addAttribute("user", loginUser);
		
		
		return "diary-write";
	}
	
	
	@GetMapping(value = "/write/{uuid}")
	public String updateDiary(Model model, HttpSession session, @PathVariable String uuid) {
		
		 UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
	        if (loginUser == null) {
	            // 로그인 안됨
	            return "redirect:/home";
	        }

	        model.addAttribute("user", loginUser);
	        
	        PostDTO post = postService.getPostByUuid(uuid);
	        
	        model.addAttribute("post",post);
		
		
		return "diary-write";
	}
	
	
	@PostMapping("/create/diary")
	public void createDiary(HttpServletRequest request, @RequestBody DiaryWriteDTO diaryWriteDTO) {
		
		PostDTO postDTO = new PostDTO(); 
		
		postDTO.setAge(diaryWriteDTO.getAge()); 
		postDTO.setContent(diaryWriteDTO.getContent()); 
		postDTO.setCreated_at(LocalDate.parse(diaryWriteDTO.getCreated_at(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		postDTO.setUser_id(diaryWriteDTO.getUserId()); 
		postDTO.setUuid(UUID.randomUUID().toString()); 
		
		if(diaryWriteDTO.getStatus().equals("일기 공개하기")) {
			postDTO.setStatus(true);
		}else {
			postDTO.setStatus(false); 
		}
		
		postService.createPost(postDTO); 
		
		
		
		
	}
	

}
