package com.lifeEgg.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lifeEgg.dto.AlarmDTO;
import com.lifeEgg.dto.AlarmPageDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.security.CustomUserDetails;
import com.lifeEgg.service.AlarmService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor 
public class AlarmController {
	
	private final AlarmService alarmService;
	
	@GetMapping(value = "/alarms")
	public String getAlarms( Model model, @RequestParam(required = false) Long page) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
        if (auth == null || !auth.isAuthenticated()) {
            // 로그인 안됨
            return "redirect:/home";
        }
    	
        CustomUserDetails detail = (CustomUserDetails) auth.getPrincipal();
        UserDTO user = detail.toUserDTO();
        
        if(page == null) {
        	page = (long) 0; 
        }
        
        model.addAttribute("user", user);
        
        AlarmPageDTO<AlarmDTO> alarms = alarmService.getAlarmsByUserId(user.getId(),page);
        alarms.setPage(page); 
        model.addAttribute("alarmPages",alarms);
        
        model.addAttribute("alarms",alarms.getContentList());
	        
		return "alarms";
	}
	
}
