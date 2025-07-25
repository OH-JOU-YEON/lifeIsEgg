package com.ohAM.lifeEgg.dtos;

import lombok.Data;

@Data 
public class UserDTO {
	
	private int id; 
	
	private String name; 
	
	
	private String email; 
	
	
	private int age; 
	
	
	private UserDTO(String name, String email, int age) {
		
		this.name = name; 
		this.email = email; 
		
		this.age = age; 
	}
	
	private static UserDTO iniMember(String name, String email,int age) {
		return new UserDTO(name,email,age); 
	}
	
	
}
