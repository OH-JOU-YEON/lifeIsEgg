package com.lifeEgg.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.lifeEgg.dto.GoogleTokenDTO;
import com.lifeEgg.dto.UserAgeDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.LoginService;
import com.lifeEgg.service.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class GoogleLoginService implements LoginService {
	
	@Value("${google.client.id}")
    private String googleClientId;
    @Value("${google.client.pw}")
    private String googleClientPw;
		
    private final UserService userService; //반드시 user -> login 방향으로만 참조할 것

    
    @Override
	public String getUrl() {
		
		String url = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri=http://localhost:8090/lifeEgg/oauth2/google"
                + "&response_type=code"
                + "&scope=email profile"
                + " https://www.googleapis.com/auth/user.birthday.read"
                + "&access_type=offline";
		return url;
	}
	
	@Override
	public String getToken(String code) {
		
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
        
        String token = "";
        if (responseEntity.getStatusCode() == HttpStatus.OK)//접속 확인
            token = responseEntity.getBody().getAccess_token(); 
        return token;
	}
	
	@Override
	public UserDTO getUserInfo(String token) {
		
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders(); //헤더 설정
        httpHeaders.setBearerAuth(token);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        
        ResponseEntity<UserDTO> userResponseEntity = restTemplate.exchange(
        		"https://www.googleapis.com/userinfo/v2/me",
                HttpMethod.GET, httpEntity, UserDTO.class); //사용자 정보
        
        UserDTO user = new UserDTO();
        
        if (userResponseEntity.getStatusCode() == HttpStatus.OK)//접속 확인
            user = userResponseEntity.getBody();
        
        return user;
	}
    
	@Override
	public int getAge(String token) {
		
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders(); //헤더 설정
        httpHeaders.setBearerAuth(token);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
		
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
        
        return age;
	}
	
	@Override
	public UserDTO login(String code) {
		String token = getToken(code);
		UserDTO user = new UserDTO();
        if (token != "") {
        	user = getUserInfo(token);
        	if (user.getEmail() != null) {
        		try {
					Long userId = userService.findUserIdByEmail(user.getEmail());//유저 db에 있는지 이메일로 확인
					
	            	if (userId != null) { //유저 존재 - 로그인
	            		
	            		user = userService.findUserById(userId);
	            		
	            	} else { //유저 없음 - 회원가입
	            			            		
	                    int age = getAge(token);
	                    user.setAge(age);
	                    userService.insertUser(user);
	            	}
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        }
        return user;
	}

}
