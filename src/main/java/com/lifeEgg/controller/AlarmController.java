package com.lifeEgg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeEgg.dto.AlarmDTO;
import com.lifeEgg.dto.AlarmPageDTO;
import com.lifeEgg.dto.CheerModalDTO;
import com.lifeEgg.dto.CheerSearchDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.AlarmService;
import com.lifeEgg.service.CheerService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor 
public class AlarmController {
	
	private final AlarmService alarmService;
	private final CheerService cheerService; 
	
	@GetMapping(value = "/alarms")
	public String getAlarms( Model model, HttpSession session,@RequestParam(required = false) Long page) {
		
		 UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
	        if (loginUser == null) {
	            // 로그인 안됨
	            return "redirect:/home";
	        }
	        
	        if(page == null) {
	        	page = (long) 0; 
	        }

	        model.addAttribute("user", loginUser);
	        
	        AlarmPageDTO<AlarmDTO> alarms = alarmService.getAlarmsByUserId(loginUser.getId(),page);
	        alarms.setPage(page); 
	        model.addAttribute("alarmPages",alarms);
	        model.addAttribute("alarms",alarms.getContentList());
	        
	        

	        
		
		
		return "alarms";
	}
	
	
	@ResponseBody 
	@PostMapping(value = "/alarms/read/cheer")
	public CheerModalDTO getCheer( Model model, HttpServletRequest request, @RequestBody CheerSearchDTO cheerSearchDTO ) {
		
		
		CheerModalDTO cheerModalDTO = cheerService.getCheerModalByid(cheerSearchDTO.getCheerId()); 
		
	      
	        
	        
	        

	        
		
		
		return cheerModalDTO; 
	}

}
