package com.lifeEgg.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.lifeEgg.dao.UserDAO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.login.google.GoogleInfResponse;
import com.lifeEgg.login.google.GoogleTokenResponse;

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
	private final UserDAO userDao;

	
	@PostMapping("/oauth2/google")
    public String loginUrlGoogle(){

        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri=http://localhost:8090/lifeEgg/oauth2/google"
                + "&response_type=code"
                + "&scope=email profile"
                + " https://www.googleapis.com/auth/profile.agerange.read"
                + "&access_type=offline";

        return "redirect:" + reqUrl;
    } 
    
    @GetMapping("/oauth2/google")
    public String loginGoogle(@RequestParam(value = "code") String code){

        RestTemplate restTemplate = new RestTemplate();
        
        //액세스 토큰 받아오기
        MultiValueMap<String, Object> tokenParams = new LinkedMultiValueMap<>();
        tokenParams.add("code", code);
        tokenParams.add("client_id", googleClientId);
        tokenParams.add("client_secret", googleClientPw);
        tokenParams.add("redirect_uri", "http://localhost:8090/lifeEgg/oauth2/google");
        tokenParams.add("grant_type", "authorization_code");
        ResponseEntity<GoogleTokenResponse> responseEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                tokenParams, GoogleTokenResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) { //접속 확인

            MultiValueMap<String, Object> infParams = new LinkedMultiValueMap<>();
            infParams.add("access_token", responseEntity.getBody().getAccess_token()); //토큰 param에 넣기

            ResponseEntity<GoogleInfResponse> infResponseEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo",
                    infParams, GoogleInfResponse.class); //사용자 정보
            
            if (infResponseEntity.getStatusCode() == HttpStatus.OK) {
//            	System.out.println(infResponseEntity.getBody().toString());
            	String email = infResponseEntity.getBody().getEmail();
				try {
					Integer userId = userDao.findUserIdByEmail(email);
            		UserDTO user = new UserDTO();
	            	if (userId != null) { //유저 존재 - 로그인
	            		user = userDao.findUserById(userId);
	            		return "";
	            	} else { //유저 없음 - 회원가입
	            		
	            		//사용자 정보 받아와서 user에 넣는 로직
	            		
	            		userDao.insertUser(user);
	            		return "";
	            	}
				} catch (Exception e) {
					e.printStackTrace();
				}
            
            }
            return "구글 로그인 요청 처리 실패";

        }
        return "구글 로그인 요청 처리 실패";
//        return "redirect:/home";
    }
	

	
}

