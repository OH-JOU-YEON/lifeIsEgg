package com.lifeEgg.service;

import org.springframework.stereotype.Service;

import com.lifeEgg.dto.UserDTO;


@Service
public interface LoginService {
	
	public String getUrl();
	
	public String getToken(String code);
	
	public UserDTO getUserInfo(String token);
	
	public int getAge(String token);
	
	public UserDTO login(String code);

}
