package com.lifeEgg.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lifeEgg.dto.CheerDTO;
import com.lifeEgg.dto.CheerWriteDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.CheerService;
import com.lifeEgg.service.PostService;
import com.lifeEgg.service.PreviewService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class CheerController {
	
	private final CheerService cheerService; 
	private final PostService postService; 
	
	
	//일기에 응원 작성하는 화면
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
	
	//응원에 응원 보내는 메서드 cheerUuid는 작성되는 응원이 아니라 부모 응원의 UUID 
	
	@GetMapping(value = "/cheer/{diaryUuid}/{cheerUuid}")
	public String writeCheerCheer( Model model, HttpSession session, @PathVariable String diaryUuid,
			@PathVariable String cheerUuid) {
		
		 UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
	        if (loginUser == null) {
	            // 로그인 안됨
	            return "redirect:/home";
	        }

	        model.addAttribute("user", loginUser);
	        model.addAttribute("diaryUuid",diaryUuid); 
	        model.addAttribute("cheerUuid", cheerUuid); 
		
		
		return "cheer-write";
	}
	
	//일기에 응원 작성 
	@PostMapping("/create/cheer")
	public ResponseEntity createCheer(HttpServletRequest request,@RequestBody CheerWriteDTO cheerWriteDTO) {
		
		CheerDTO cheerDTO = new CheerDTO(); 
		
		String content = cheerWriteDTO.getContent(); 
		
		cheerDTO.setContent(content);
		cheerDTO.setUser_id(cheerWriteDTO.getUserId());
		cheerDTO.setUuid(UUID.randomUUID().toString());
		cheerDTO.setPost_id(postService.getPostIdByUuid(cheerWriteDTO.getDiaryUuid())); 
		cheerDTO.setPreview(PreviewService.getPreview(content)); 
		
		//응원 uuid 변수가 존재하면 그걸로 응원 검색해서 아이디 받아와서 등록함. 
		
		if(cheerWriteDTO.getCheerUuid() != null) {
			
			cheerDTO.setParent_id(cheerService.getCheerIdByUuid(cheerWriteDTO.getCheerUuid())); 
		}
		
		cheerService.createCheer(cheerDTO); 
		
		return new ResponseEntity(HttpStatus.OK);
		
		
	}
	

	

}
