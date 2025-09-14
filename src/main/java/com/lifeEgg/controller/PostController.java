package com.lifeEgg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.PostPageDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService; 
	
	@GetMapping(value = "/diaries")
	public String getMyPages(Model model, HttpSession session,@RequestParam(required = false) Long page) {
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		if (loginUser == null) {
			// 로그인 안됨
			return "redirect:/home";
		}
		
		if(page == null) {
			page = (long) 0; 
		}

		model.addAttribute("user", loginUser);	        
	        
		//임시로 포스트 컨텐츠 전부를 던짐. 뒤에 미리보기 추가할 것 
		PostPageDTO<PostDTO> postPages = postService.getPostsByUserId(loginUser.getId(),page);
		model.addAttribute("postPages",postPages);
		model.addAttribute("posts",postPages.getContentList());
		
		return "mypages";
	}
	

}
