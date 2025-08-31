package com.lifeEgg.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.lifeEgg.dto.GoogleTokenDTO;
import com.lifeEgg.dto.UserAgeDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.UserService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class LoginController {

	@Value("${google.client.id}")
    private String googleClientId;
    @Value("${google.client.pw}")
    private String googleClientPw;
    

//	
	private final UserService userService;

	
	@PostMapping("/oauth2/google")
    public String loginUrlGoogle(){

        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri=http://localhost:8090/lifeEgg/oauth2/google"
                + "&response_type=code"
                + "&scope=email profile"
                + " https://www.googleapis.com/auth/user.birthday.read"
                + "&access_type=offline";

        return "redirect:" + reqUrl;
    } 
    
    @GetMapping("/oauth2/google")
    public String loginGoogle(@RequestParam(value = "code") String code, HttpServletRequest request){

        RestTemplate restTemplate = new RestTemplate();
        
        //액세스 토큰 받아오기
        MultiValueMap<String, Object> tokenParams = new LinkedMultiValueMap<>();
        tokenParams.add("code", code);
        tokenParams.add("client_id", googleClientId);
        tokenParams.add("client_secret", googleClientPw);
        tokenParams.add("redirect_uri", "http://localhost:8090/lifeEgg/oauth2/google");
        tokenParams.add("grant_type", "authorization_code");
        ResponseEntity<GoogleTokenDTO> responseEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                tokenParams, GoogleTokenDTO.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) { //접속 확인

            String token = responseEntity.getBody().getAccess_token();            
            
            HttpHeaders httpHeaders = new HttpHeaders(); //헤더 설정
            httpHeaders.setBearerAuth(token);
            HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
            
            ResponseEntity<UserDTO> userResponseEntity = restTemplate.exchange(
            		"https://www.googleapis.com/userinfo/v2/me",
                    HttpMethod.GET, httpEntity, UserDTO.class); //사용자 정보
            
            if (userResponseEntity.getStatusCode() == HttpStatus.OK) { //접속 확인
            	
                UserDTO user = userResponseEntity.getBody(); //유저 정보 받아오기
                
				try {
					Integer userId = userService.findUserIdByEmail(user.getEmail());
					
	            	if (userId != null) { //유저 존재 - 로그인
	            		user = userService.findUserById(userId);
	            		
	                    HttpSession session = request.getSession();
	                    session.setAttribute("loginUser", user);
	                    
	            		return "redirect:/home";
	            		
	            	} else { //유저 없음 - 회원가입
	            			            		
	                    //People API에서 연령 받아오기
	                    ResponseEntity<UserAgeDTO> birthResponseEntity = restTemplate.exchange("https://people.googleapis.com/v1/people/me?"
	                    		+ "personFields=birthdays",HttpMethod.GET, httpEntity, UserAgeDTO.class);
	                    
	                    Integer birthYear = null;
	                    if (birthResponseEntity.getStatusCode() == HttpStatus.OK) {
	                    	birthYear = birthResponseEntity.getBody().getBirthdays().get(0).getDate().getYear();
	                    	System.out.println("birthYear: " + birthYear);
	                    }
	                    
	                    int age = 0;
	                    
	                    if (birthYear != null) {
	                    	int nowYear = LocalDate.now().getYear();
	                    	System.out.println("nowYear: " + nowYear);
	                    	if (nowYear > birthYear) {
	                    		age = nowYear - birthYear;
		                    	System.out.println("age: " + age);
	                    	}
	                    }
	                    
	                    user.setAge(age);
	                    
	                    userService.insertUser(user);
	                    
	                    HttpSession session = request.getSession();
	                    session.setAttribute("loginUser", user);
	                    
	            		return "redirect:/home";
	            	}
				} catch (Exception e) {
					e.printStackTrace();
				}
            
            }
            return "구글 로그인 요청 처리 실패"; // 처리 확인용 임시

        }
        return "구글 로그인 요청 처리 실패";
    }
    
    
    @GetMapping(value="/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "redirect:/home";
    }
	

	
}

