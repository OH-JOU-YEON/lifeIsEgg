package com.lifeEgg.controller;

import java.util.Map;
import java.util.HashMap;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.lifeEgg.dao.UserDAO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.login.google.GoogleRequest;
import com.lifeEgg.login.google.GoogleResponse;



@Controller
@PropertySource("classpath:application.properties")
public class LoginController {

	@Value("${google.client.id}")
    private String googleClientId;
    @Value("${google.client.pw}")
    private String googleClientPw;
    
//	@Autowired
	private UserDAO userDao;

	
	
    @RequestMapping(value="/oauth2/google", method = RequestMethod.POST)
    public String loginUrlGoogle(){

        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri=http://localhost:8090/lifeEgg/oauth2/google"
                + "&response_type=code"
                + "&scope=email profile"
//                + " https://www.googleapis.com/auth/user.birthday.read"
                + "&access_type=offline";
        return "redirect:" + reqUrl;
    }
    
    @RequestMapping(value="/oauth2/google", method = RequestMethod.GET)
    public String loginGoogle(@RequestParam(value = "code") String authCode) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        GoogleRequest googleOAuthRequestParam = GoogleRequest
                .builder()
                .clientId(googleClientId)
                .clientSecret(googleClientPw)
                .code(authCode)
                .redirectUri("http://localhost:8090/lifeEgg/oauth2/google")
                .grantType("authorization_code").build();
        ResponseEntity<GoogleResponse> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                googleOAuthRequestParam, GoogleResponse.class);
        String jwtToken=resultEntity.getBody().getId_token();
//        Map<String, String> map=new HashMap<>();
//        map.put("id_token",jwtToken);
//        ResponseEntity<GoogleInfResponse> resultEntity2 = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo",
//                map, GoogleInfResponse.class);
//        String email = resultEntity2.getBody().getEmail();    
//        String name = resultEntity2.getBody().getName();
//    	System.out.println(email);
//    	System.out.println(name);

        return "redirect:/home";
    }
	

	
}

