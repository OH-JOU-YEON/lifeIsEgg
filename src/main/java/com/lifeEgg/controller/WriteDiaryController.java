package com.lifeEgg.controller;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeEgg.dto.DiaryWriteDTO;
import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.PostDeleteDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.PostService;
import com.lifeEgg.service.PreviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
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
	
	@GetMapping(value = "/write/date")
	@ResponseBody
	public Map<String, Object> findDiary(@RequestParam("date") String date) {
		
		System.out.println(date);
		
		Map<String, Object> response = new HashMap<>();
		LocalDate created_at = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
		PostDTO post = postService.getPostByCreated(created_at);
		
		if (post != null) {
			System.out.println(post.toString());
			
			response.put("exists", true);
			response.put("uuid", post.getUuid()); //post를 통으로 보내면 직렬화 문제가 생김
		} else {
			response.put("exists", false);
		}
		return response;
	}
	
	
	@PostMapping("/create/diary")
	public ResponseEntity createDiary(HttpServletRequest request, @RequestBody DiaryWriteDTO diaryWriteDTO) {
		
		PostDTO postDTO = new PostDTO(); 
		
		String content = diaryWriteDTO.getContent(); 
		
		postDTO.setAge(diaryWriteDTO.getAge()); 
		postDTO.setContent(content);
		postDTO.setPreview(PreviewService.getPreview(content)); 
		postDTO.setCreated_at(LocalDate.parse(diaryWriteDTO.getCreated_at(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		postDTO.setUser_id(diaryWriteDTO.getUserId()); 
		postDTO.setUuid(UUID.randomUUID().toString()); 
		
		if(diaryWriteDTO.getStatus().equals("일기 공개하기")) {
			postDTO.setStatus(true);
		}else {
			postDTO.setStatus(false); 
		}
		
		postService.createPost(postDTO); 
		
		
		return new ResponseEntity(HttpStatus.OK);
		
		
		
		
	}
	
	
	@PostMapping("diary/delete")
	public ResponseEntity deleteDiary(HttpServletRequest request, @RequestBody PostDeleteDTO postDeleteDTO) {
		
		
		
		postService.deletePost(postDeleteDTO.getUuid()); 
		
		
		return new ResponseEntity(HttpStatus.OK);
	}
	

}
