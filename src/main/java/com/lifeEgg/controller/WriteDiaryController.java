package com.lifeEgg.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller 
public class WriteDiaryController {
	
	@GetMapping(value = "/write")
	public String home( Model model) {
		
		
		return "diary_write";
	}

}
