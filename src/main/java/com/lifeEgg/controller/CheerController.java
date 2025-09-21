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

import com.lifeEgg.dto.AlarmDTO;
import com.lifeEgg.dto.CheerDTO;
import com.lifeEgg.dto.CheerWriteDTO;
import com.lifeEgg.dto.PostAlarmDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.AlarmService;
import com.lifeEgg.service.CheerService;
import com.lifeEgg.service.PostService;
import com.lifeEgg.service.PreviewService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class CheerController {
	
	private final CheerService cheerService; 
	private final PostService postService; 
	private final AlarmService alarmService; 
	
	
	//일기에 응원 작성하는 화면
	@GetMapping(value = "/writeCheer/{diaryUuid}")
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
	
	@GetMapping(value = "/writeCheer/{diaryUuid}/{cheerUuid}")
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
	

		
		//응원 보는 화면 메서드 
		
		@GetMapping(value = "/cheer/{cheerUuid}")
		public String getCheer( Model model, HttpSession session, 
				@PathVariable String cheerUuid) {
			
			 UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		        if (loginUser == null) {
		            // 로그인 안됨
		            return "redirect:/home";
		        }

		        model.addAttribute("user", loginUser);
		        
		        CheerDTO cheerDTO = cheerService.getCheerByUuid(cheerUuid); 
		        
		        model.addAttribute("cheer", cheerDTO); 
		        
		        //응원의 원본 포스트, 그 전 응원 검사해서 가져오는 메서드 
		        
		        String postUuid = postService.getUuidById(cheerDTO.getPost_id()); 
		        model.addAttribute("diaryUuid", postUuid); 
		        
		        if(cheerDTO.getParent_id() != null) {
		        String parentUuid = cheerService.getUuidById(cheerDTO.getParent_id());  
		        model.addAttribute("cheerUuid", parentUuid);
		        }
		        
			
			
			return "cheer";
		}
	
	//일기에 응원 작성 
	@PostMapping("/create/cheer")
	public ResponseEntity createCheer(HttpServletRequest request,@RequestBody CheerWriteDTO cheerWriteDTO) {
		
		CheerDTO cheerDTO = new CheerDTO(); 
		AlarmDTO alarmDTO = new AlarmDTO(); 
		String postUuid = cheerWriteDTO.getDiaryUuid(); 
		PostAlarmDTO postAlarm = postService.getPostAlarmByUuid(postUuid); 
		
		String content = cheerWriteDTO.getContent(); 
		
		cheerDTO.setContent(content);
		cheerDTO.setUser_id(cheerWriteDTO.getUserId());
		cheerDTO.setUuid(UUID.randomUUID().toString());
		cheerDTO.setPost_id(postService.getPostIdByUuid(cheerWriteDTO.getDiaryUuid())); 
		cheerDTO.setPreview(PreviewService.getPreview(content)); 
		alarmDTO.setUuid(UUID.randomUUID().toString()); 
		alarmDTO.setPost_uuid(postUuid); 
		alarmDTO.setUser_id(postAlarm.getUserId()); 
		
		
	
	
		
		
		
	
		
		if(cheerWriteDTO.getCheerUuid() != null) {
			
			String cheerUuid = cheerWriteDTO.getCheerUuid(); 
			
			cheerDTO.setParent_id(cheerService.getCheerIdByUuid(cheerUuid)); 
			
			
			
			alarmDTO.setCheer_uuid(cheerUuid); 
			
			StringBuilder sb = new StringBuilder(cheerService.getCheerPreviewByUuid(cheerUuid) + "...에 답장이 도착했습니다."); 
			alarmDTO.setContent(sb.toString());
			
			
		} else {
			StringBuilder sb = new StringBuilder(postAlarm.getCreated_at() + "의 일기 " + postAlarm.getPostPreview() 
			+ "...에 응원이 달렸습니다.\n" + PreviewService.getPreview(content)); 
			alarmDTO.setContent(sb.toString()); 
		}
		
		cheerService.createCheer(cheerDTO); 
		alarmService.createAlarm(alarmDTO);
		
		
		return new ResponseEntity(HttpStatus.OK);
		
		
	}
	

	

}
