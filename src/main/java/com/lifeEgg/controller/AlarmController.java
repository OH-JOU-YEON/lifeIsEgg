package com.lifeEgg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lifeEgg.dto.AlarmDTO;
import com.lifeEgg.dto.AlarmPageDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.AlarmService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor 
public class AlarmController {
	
	private final AlarmService alarmService;
	
	@GetMapping(value = "/alarms")
	public String getAlarms( Model model, HttpSession session) {
		
		 UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
	        if (loginUser == null) {
	            // 로그인 안됨
	            return "redirect:/home";
	        }

	        model.addAttribute("user", loginUser);
	        
	        AlarmPageDTO<AlarmDTO> alarms = alarmService.getAlarmsByUserId(loginUser.getId() );
	        model.addAttribute("alarmPages",alarms);
	        model.addAttribute("alarms",alarms.getContentList());
	        
	        

	        
		
		
		return "alarms";
	}

}
