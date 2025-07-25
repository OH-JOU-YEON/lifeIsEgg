package com.ohAM.lifeEgg.vos;

import lombok.Getter;

@Getter 
public class UserMemberVO {
	
	private final String name; 
	
	private final String email; 
	
	private final int age; 
	
	
	
	public UserMemberVO(String name, String email, int age) {
		
		this.name = name; 
		this.email = email; 
		this.age = age; 
		
	}
	


}
