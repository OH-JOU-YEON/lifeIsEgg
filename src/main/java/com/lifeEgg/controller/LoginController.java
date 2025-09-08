package com.lifeEgg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.LoginService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	
	@PostMapping("/oauth2/google")
    public String loginUrlGoogle(){

        String reqUrl = loginService.getUrl();
        return "redirect:" + reqUrl;
        
    } 
    
    @GetMapping("/oauth2/google")
    public String loginGoogle(@RequestParam(value = "code") String code, HttpServletRequest request){
    	
    	UserDTO user = loginService.login(code);
    	
    	HttpSession session = request.getSession();
        request.changeSessionId();
        session.setAttribute("loginUser", user);
        
        return "redirect:/home";

    }
    
    
    @GetMapping(value="/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "redirect:/home";
        
    }
	

	
}

