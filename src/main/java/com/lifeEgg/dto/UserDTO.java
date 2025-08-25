package com.lifeEgg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data 
public class UserDTO {
	
	@JsonIgnore
    private int id;
	
    private String name;
    private int age;
    private String email;
}